package br.com.gelatos;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



@ViewScoped
@ManagedBean(name = "gelatosBean")
public class GelatoBean {
		
	private List<Gelato> gelatos = new ArrayList<Gelato>();
	private List<Gelato> filteredGelatos;

	public List<Gelato> getFilteredGelatos() {
	    return filteredGelatos;
	}
	
	
		public GelatoBean() {
			setGelato(new Gelato());
		}

		public Gelato gelato;
		
		public Gelato getGelato() {
			return gelato;
		}
		public void setGelato(Gelato gelato) {
			this.gelato = gelato;
		}
		
		public List<Gelato> getGelatos() {
			return gelatos;
		}
		
		public void setGelatos(List<Gelato> gelatos) {
			this.gelatos = gelatos;
		}
		
		public void validarCampos() {
		    //FacesContext context = FacesContext.getCurrentInstance();
		    
		    // testar se os campos necessarios estão vazios
		    if (this.gelato.getNome() == null || this.gelato.getNome().isEmpty()) {
		        
		        JSFUtil.adicionarMensagemErro("Nome é obrigatório!");
		    }
		    if (this.gelato.getTipo() == null || this.gelato.getTipo().isEmpty()) {
		        
		    
		        JSFUtil.adicionarMensagemErro("Tipo é obrigatório!");
		        	
		    }
		    if (this.gelato.getPreco() == null) {
		        
		   
		        JSFUtil.adicionarMensagemErro("Preço é obrigatório!");
		        
		    }
		    
		    	else {
		    	
		    	//JSFUtil.adicionarMensagemSucesso("Gelato cadastrado com sucesso");
		        inserirGelato();
		    }
		}
		
		///////////////////////////////////////////////////////////////
		
		public String inserirGelato() {
			GelatoDAO gelatoDAO = new GelatoDAO();
			gelatoDAO.atualizarGelato(gelato);
			carregarGelatos();
			limparGelato();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Gravados com Sucesso!"));
			
			return "";
		}

		public String removerGelato() {
			GelatoDAO gelatoDAO = new GelatoDAO();
			gelatoDAO.removerPorId(getGelato());
			carregarGelatos();
			limparGelato();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso!"));
			
			return "";
		}
			
		@PostConstruct
		public void carregarGelatos() {
			GelatoDAO gelatoDAO = new GelatoDAO();
			setGelatos(gelatoDAO.getListEntity());
		}
		
		public String limparGelato() {
			setGelato(new Gelato());
			return "";
		}

		public void setFilteredGelatos(List<Gelato> filteredGelatos) {
		    this.filteredGelatos = filteredGelatos;
		}
		
	}
