/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package img.read;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Random;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author SUBA
 */
public class ImgProcess {
   public String result = null;
    String outpath=null;
    Random r=new Random();
    int rr=r.nextInt(1000);
    public String Process(String file,String opath)
    {
        outpath=opath;
        File imageFile = new File(file);
Tesseract instance = Tesseract.getInstance(); //

try {

 result = instance.doOCR(imageFile);
//System.out.println(result);

} catch (TesseractException e) {
System.err.println(e.getMessage());
}
return result;
    }
    public String pdf()
    {
         Document document = new Document();
         String fpath=outpath+"\\ITR_"+rr+".pdf";
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fpath));
         document.open();
         document.add(new Paragraph(result));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return fpath;
    }
    public String doc()
    {
        String fpath=outpath+"\\ITR_"+rr+".docx";
        try{
        XWPFDocument document= new XWPFDocument();
   //Write the Document in file system
   FileOutputStream out = new FileOutputStream(new File(fpath));

   //create Paragraph
   XWPFParagraph paragraph = document.createParagraph();
   XWPFRun run=paragraph.createRun();
   run.setText(result);
   document.write(out);
   out.close();
        }catch(Exception ee){}
   return fpath;
    }
    public String txt()
    {
        String fpath=outpath+"\\ITR_"+rr+".txt";
        Writer writer = null;

try {
    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fpath), "utf-8"));
    writer.write(result);
} catch (IOException ex) {
  // report
} finally {
   try {writer.close();} catch (Exception ex) {/*ignore*/}
}
   return fpath;
    }
}
