import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) throws IOException, PrinterException {
        TicketGenerator<Institution> ticketGenerator = new TicketGenerator<>();

        Ticket<Institution> ticket = ticketGenerator.generate("Dysil, Silatsam", new Cinema());
        /*Ticket<Institution> ticket1 = ticketGenerator.generate("Dylan, Silatsam", new Restaurant());
        Ticket<Institution> ticket2 = ticketGenerator.generate("Dylan, Silatsam", new Restaurant());*/

        PDDocument document = ticketGenerator.build(ticket);
        Desktop.getDesktop().open(new File("./PDF_Tickets/Ticket.pdf"));
        /*ticketGenerator.build(ticket1);
        Desktop.getDesktop().open(new File("./PDF_Tickets/Ticket.pdf"));
        ticketGenerator.build(ticket2);
        Desktop.getDesktop().open(new File("./PDF_Tickets/Ticket.pdf"));*/


        PrintService myPrintService = findPrintService("HP DeskJet Plus 4100 series 2"); // My Printer

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(myPrintService);
        //job.print();
    }

    private static PrintService findPrintService(String printerName) {
        PrintService result;
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        result = Arrays.stream(printServices).filter(printService -> printService.getName().trim().equals(printerName)).findFirst().orElse(null);
        return result;
    }

}
