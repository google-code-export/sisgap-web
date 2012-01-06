package pe.com.mmh.sisgap.seguridad.action.form;

import org.apache.struts.action.ActionForm;

public class LoginActionForm extends ActionForm {
	private String txtUserName;
	private String txtPassword;
		
	private String txtNuevoPassword;
	private String txtNuevoPassword2;
	

	public String getTxtNuevoPassword2() {
		return txtNuevoPassword2;
	}
	public void setTxtNuevoPassword2(String txtNuevoPassword2) {
		this.txtNuevoPassword2 = txtNuevoPassword2;
	}
	public String getTxtNuevoPassword() {
		return txtNuevoPassword;
	}
	public void setTxtNuevoPassword(String txtNuevoPassword) {
		this.txtNuevoPassword = txtNuevoPassword;
	}
	public String getTxtUserName() {
		return txtUserName;
	}
	public void setTxtUserName(String txtUserName) {
		this.txtUserName = txtUserName;
	}
	public String getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}


}
