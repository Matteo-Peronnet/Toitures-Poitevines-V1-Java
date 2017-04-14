/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27080565/itext-make-a-single-letter-bold-within-a-word
 */
package ihm.panneaux;
 import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TableBorder {
 
    public static final String DEST = "results/tables/table_border_outer_only.pdf";
 
    public static void main(String[] args) throws IOException,
        DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableBorder().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(6);
        table.setTableEvent(new BorderEvent());
        table.getDefaultCell().setBorder(Rectangle.RECTANGLE);
        for(int aw = 0; aw < 25; aw++){
            table.addCell("hi");
        }
        document.add(table);
        document.close();
    }
 
    public class BorderEvent implements PdfPTableEvent {
        public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
            float width[] = widths[0];
            float x1 = width[0];
            float x2 = width[width.length - 1];
            float y1 = heights[0];
            float y2 = heights[heights.length - 1];
            PdfContentByte cb = canvases[PdfPTable.LINECANVAS];
            cb.rectangle(x1, y1, x2 - x1, y2 - y1);
            cb.stroke();
            cb.resetRGBColorStroke();
        }
    }
}