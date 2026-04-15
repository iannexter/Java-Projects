package br.com.gelatos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Gelato> gelatos;
    private List<Gelato> carrinho;
    private BigDecimal total;
    private VendaDAO vendaDAO;
    private GelatoDAO gelatoDAO;

    public VendaBean() {
        gelatoDAO = new GelatoDAO();
        vendaDAO = new VendaDAO();
        carrinho = new ArrayList<>();
        total = BigDecimal.ZERO;
    }

    @PostConstruct
    public void init() {
        gelatos = gelatoDAO.getListEntity();
    }

    public List<Gelato> getGelatos() {
        return gelatos;
    }

    public List<Gelato> getCarrinho() {
        return carrinho;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void adicionarAoCarrinho(Gelato gelato) {
        carrinho.add(gelato);
        total = total.add(gelato.getPreco());
    }

    public void removerDoCarrinho(Gelato gelato) {
        carrinho.remove(gelato);
        total = total.subtract(gelato.getPreco());
    }

    public void finalizarVenda() {
        if (carrinho.isEmpty()) {
        	
        	JSFUtil.adicionarMensagemErro("A venda não pode ser realizada pois não há nenhum item no carrinho!");
            
            return; 
        }
        
        Venda venda = new Venda();
        venda.setGelatos(new ArrayList<>(carrinho));
        venda.setTotal(total);
        vendaDAO.incluirVenda(venda);
        carrinho.clear();
        total = BigDecimal.ZERO;
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Venda realizada com sucesso!"));
        
    }

}
