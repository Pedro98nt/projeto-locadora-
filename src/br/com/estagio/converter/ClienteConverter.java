package br.com.estagio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.estagio.dao.ClienteDao;
import br.com.estagio.modelo.Cliente;

@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

	private ClienteDao dao = new ClienteDao();

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
			return String.valueOf(((Cliente) valor).getId());
		}
		return null;
	}

}
