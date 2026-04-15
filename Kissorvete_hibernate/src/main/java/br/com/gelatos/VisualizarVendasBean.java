package br.com.gelatos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;


@ManagedBean
@ViewScoped
public class VisualizarVendasBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Venda> vendas;
    private Venda vendaSelecionada; 
    private VendaDAO vendaDAO;
    private DateTimeFormatter dateFormatter; // arrumar a data

    public VisualizarVendasBean() {
        vendaDAO = new VendaDAO();
        dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data
        atualizarListaVendas();
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Venda getVendaSelecionada() {
        return vendaSelecionada;
    }

    public void setVendaSelecionada(Venda vendaSelecionada) {
        this.vendaSelecionada = vendaSelecionada;
    }

    public void atualizarListaVendas() {
        vendas = vendaDAO.getListEntity();
    }

    public void removerVenda() {
        if (vendaSelecionada != null) {
            vendaDAO.removerVenda(vendaSelecionada);
            atualizarListaVendas(); 
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Venda removida com sucesso!"));
        }
    }

    public String formatarData(LocalDate data) {
        return data.format(dateFormatter);
    }
    
    public void gerarPDF() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();

           
            Paragraph totalVendas = new Paragraph("Total de Vendas");
            totalVendas.setAlignment(Paragraph.ALIGN_CENTER);
            totalVendas.setSpacingAfter(10); 
            document.add(totalVendas);

            
            PdfPTable table = new PdfPTable(3); 

           
            table.addCell("Número");
            table.addCell("Data");
            table.addCell("Valor");

            
            for (Venda venda : vendas) {
                table.addCell(String.valueOf(venda.getId()));
                table.addCell(formatarData(venda.getDataVenda()));
                table.addCell(String.valueOf(venda.getTotal()));
            }

            
            document.add(table);
            document.close();

            // Obter os bytes do PDF
            byte[] pdfBytes = baos.toByteArray();

            
            StreamedContent file = DefaultStreamedContent.builder()
                .name("lista_vendas.pdf")
                .contentType("application/pdf")
                .stream(() -> new ByteArrayInputStream(pdfBytes))
                .build();

            
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseContentLength(pdfBytes.length);
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=lista_vendas.pdf");
            externalContext.addResponseCookie("fileDownload", "true", null);
            externalContext.getResponseOutputStream().write(pdfBytes);
            externalContext.responseFlushBuffer();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
