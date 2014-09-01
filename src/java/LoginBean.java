
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcusvgl
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    
    public void validateCaptcha(FacesContext context,
                                UIComponent componentToValidate,
                                Object value)
                                throws ValidatorException{

        Captcha secretcaptcha = (Captcha) context.getExternalContext()
                                                .getSessionMap()
                                                .get(Captcha.NAME);
        
        if (secretcaptcha.isCorrect(value.toString())){
            
            return;
        }// Fim do if

        throw new ValidatorException(new FacesMessage("Captcha inválido!"));
    }// Fim do metodo validateCaptcha
}