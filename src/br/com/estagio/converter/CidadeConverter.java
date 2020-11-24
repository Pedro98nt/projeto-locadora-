package br.com.estagio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.estagio.dao.CidadeDao;
import br.com.estagio.modelo.Cidade;

@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {

	private CidadeDao dao = new CidadeDao();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Cidade) valor).getId());
		}
		return null;
	}

}
