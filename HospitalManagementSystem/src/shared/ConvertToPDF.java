/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import hospitalmanagementsystem.models.Invoice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NIAR Tech
 */
public class ConvertToPDF {

    public void convertObjToPDF(Invoice invoice) {
        Date date = new Date();
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 25, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Font blueFont2 = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
        Document document = new Document();
        try {
//            Invoice invoice = new Invoice("John", "Frost", 22, "Med1", 250, "Credit Card");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./data/" + invoice.getName() + date.getTime() + "_invoice.pdf"));
            document.open();

            PdfPTable table1 = new PdfPTable(1);
            table1.setWidthPercentage(100);
            table1.setSpacingBefore(10f);
            table1.setSpacingAfter(10f);
            float[] columnWidths = {1f};
            table1.setWidths(columnWidths);

            PdfPCell table1_cell1 = new PdfPCell(new Paragraph("INVOICE", blueFont));
            table1_cell1.setPaddingLeft(10);
            table1_cell1.setBorder(Rectangle.NO_BORDER);
            table1_cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1_cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table1.addCell(table1_cell1);
            document.add(table1);

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(10f);
            table2.setSpacingAfter(10f);
            float[] columnWidths2 = {1f, 1f};
            table2.setWidths(columnWidths2);

            PdfPCell table2_c1 = new PdfPCell(new Paragraph("SUNSHINE HOSPITAL", blueFont2));
            table2_c1.setPaddingLeft(10);
            table2_c1.setBorder(Rectangle.NO_BORDER);
            table2_c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2_c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//System.out.println(date.getDate());
            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            PdfPCell table2_c2 = new PdfPCell(new Paragraph(df.format(date.getTime()), blueFont2));
            table2_c2.setPaddingLeft(10);
            table2_c2.setBorder(Rectangle.NO_BORDER);
            table2_c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2_c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            table2.addCell(table2_c1);
            table2.addCell(table2_c2);
            document.add(table2);

            document.add(new Paragraph("Patient Name: " + invoice.getName()));
            document.add(new Paragraph("phone: " + invoice.getPhone()));
            document.add(new Paragraph("gender: " + invoice.getGender()));
            document.add(new Paragraph("Counsultancy Fees: " + invoice.getAppointmentCharges()));
            document.add(new Paragraph("Medicines: " + invoice.getMedicineCharges()));
            document.add(new Paragraph("GST: " + invoice.getGST()));
            document.add(new Paragraph("Total: " + invoice.getAmount()));
            document.add(new Paragraph("Payment Method : " + invoice.getPayment_method()));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
