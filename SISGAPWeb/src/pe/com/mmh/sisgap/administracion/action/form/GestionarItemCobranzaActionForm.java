package pe.com.mmh.sisgap.administracion.action.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class GestionarItemCobranzaActionForm extends ActionForm{

	private FormFile file;
	private String txtBusqueda;
	private String codigo;


	public String getTxtBusqueda() {
		return txtBusqueda;
	}

	public void setTxtBusqueda(String txtBusqueda) {
		this.txtBusqueda = txtBusqueda;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
