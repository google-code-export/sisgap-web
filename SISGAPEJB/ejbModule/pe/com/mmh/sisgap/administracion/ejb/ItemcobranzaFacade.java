/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.Itemcobranza;

/**
 *
 * @author ANDREA
 */
@Stateless
public class ItemcobranzaFacade implements ItemcobranzaFacadeLocal {
	
	private static final String view_buscar_item = "select * from view_buscar_item where str_descripcion like %s ";
	private static final String SP_DEL_ITEMCOB = "{call PKG_ADMINISTRACION.SP_DEL_ITEMCOB(?)}";
	private static final String SP_UPD_ITEMCOB = "{call PKG_ADMINISTRACION.SP_UPD_ITEMCOB(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	@Resource(mappedName="java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
    @PersistenceContext
    private EntityManager em;

    public void create(Itemcobranza itemcobranza) {
        em.persist(itemcobranza);
    }

    public void edit(Itemcobranza ic) {
    	Connection connection = null;
    	CallableStatement cst = null;
    	String tipo = "";
    	try {			
    		connection = getConnection();
			cst = connection.prepareCall(SP_UPD_ITEMCOB);
			cst.setInt("P_COD_ITEMCOBRANZA", ic.getCodItemcobranza().intValue());
			cst.setBigDecimal("P_NUM_COSTO", ic.getNumCosto());
			cst.setString("P_STR_MONEDA", ""+ic.getStrMoneda());
			cst.setString("P_STR_TIPOCOBRANZA", ""+ic.getStrTipocobranza());
			cst.setShort("P_NUM_ESTADO", ic.getNumEstado());
			cst.setString("P_STR_DESCRIPCION", ic.getStrDescripcion());
			cst.setInt("P_COD_UNIMEDIDA", ic.getUnidadmedida().getCodUnimedida().intValue());
			cst.setLong("P_COD_ITEMPADRE", ic.getNumCodItemPadre());
			cst.setInt("P_COD_LUZORIG", ic.getCodReciboLuz().intValue());
			if(ic.getStrTipo()==null)
				tipo = "I";
			cst.setString("P_TIPO", tipo);
			cst.setDate("P_FECHAFIN", (Date) ic.getDatFechaFin());
			cst.setString("P_FLGVARIABLE", ic.getStrFlgVariable());
			cst.setBigDecimal("P_NUMESTADO", ic.getNumCobroAdicional());
			cst.setString("P_FLGCOBROSOCIO", ic.getStrFlgCobroSocio());
			cst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(cst!=null){cst.close();}
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    }

    public void remove(BigDecimal codigo) {
    	
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {			
    		connection = getConnection();
			cst = connection.prepareCall(SP_DEL_ITEMCOB);
			cst.setInt(1, codigo.intValue());
			cst.execute();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(cst!=null){cst.close();}
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
    }

    public Itemcobranza find(Object id) {
        return em.find(Itemcobranza.class, id);
    }

    public List<Itemcobranza> findAll() {
        return em.createQuery("select object(o) from Itemcobranza as o").getResultList();
    }

	
    public List<Itemcobranza> buscarxNombre(String nombre){
    	Connection connection = null;
		PreparedStatement pst;
		ResultSet rs;
		List<Itemcobranza> lsItemCobranza = null;
		Itemcobranza itemcobranza = null;
		try {
			connection = getConnection();
			lsItemCobranza = new ArrayList<Itemcobranza>();
			pst=connection.prepareStatement(String.format(view_buscar_item, "'%"+nombre.toUpperCase()+"%'"));
			rs = pst.executeQuery();
			
			while(rs.next()){
				//COD_ITEMCOBRANZA,NUM_COSTO,STR_MONEDA,STR_TIPOCOBRANZA,NUM_ESTADO,STR_DESCRIPCION,COD_RECIBOLUZ,STR_TIPO,DAT_FECHAFIN,STR_FLGVARIABLE,NUM_COBROADICIONAL

				itemcobranza = new Itemcobranza();
				itemcobranza.setCodItemcobranza(rs.getBigDecimal("COD_ITEMCOBRANZA"));
				itemcobranza.setNumCosto(rs.getBigDecimal("NUM_COSTO"));
				itemcobranza.setStrMoneda(rs.getString("STR_MONEDA"));
				itemcobranza.setStrTipocobranza(rs.getString("STR_TIPOCOBRANZA"));
				itemcobranza.setStrDescripcion(rs.getString("STR_DESCRIPCION"));
				itemcobranza.setCodReciboLuz(rs.getBigDecimal("COD_RECIBOLUZ"));
				itemcobranza.setStrTipo(rs.getString("STR_TIPO"));
				itemcobranza.setDatFechaFin(rs.getDate("DAT_FECHAFIN"));
				itemcobranza.setStrFlgVariable(rs.getString("STR_FLGVARIABLE"));
				itemcobranza.setNumCobroAdicional(rs.getBigDecimal("NUM_COBROADICIONAL"));
				lsItemCobranza.add(itemcobranza);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(connection!=null){connection.close();}					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lsItemCobranza;    	
    }
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
