package hu.neuron.bean;

import java.io.Serializable;
import java.util.Locale;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@SuppressWarnings("serial")
@ManagedBean(name="language")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class LanguageBean implements Serializable{
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
 
    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
    
    public String getLanguage() {
        return locale.getLanguage();
    }
}
