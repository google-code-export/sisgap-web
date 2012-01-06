package pe.com.mmh.sisgap.administracion.action.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


public class SuministroLuzActionForm extends ActionForm{	
	
	private FormFile file;
	
	private String txtNombre;
	private String txtCodigo;
	private String cmbAccion;

	
	public String getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public String getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(String txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public String getCmbAccion() {
		return cmbAccion;
	}

	public void setCmbAccion(String cmbAccion) {
		this.cmbAccion = cmbAccion;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
}
