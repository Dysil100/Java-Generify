import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketGenerator <e extends Institution> {
    BigInteger counter = BigInteger.ZERO;
    Set<Ticket<e>> tickets = new HashSet<>();

    public Ticket<e> generate(String name, e institution){
        counter = counter.add(BigInteger.valueOf(Long.parseLong("1")));
        Ticket<e> newTicket = new Ticket<>(institution, counter, name);
        tickets.add(newTicket);
        return newTicket;
    }

    public PDDocument build(Ticket<e> ticket) throws IOException {

        PDDocument document = new PDDocument();
        //PDPage page = new PDPage();
        document.addPage(new PDPage());
        List<String> speech = Arrays.asList(ticket.toString().split("\n"));
        PDPageContentStream pageContentStream = new PDPageContentStream(document, document.getPage(0));

        pageContentStream.setFont( PDType1Font.HELVETICA_BOLD_OBLIQUE, 26 );
        write(speech.get(0), pageContentStream, 700, Color.GREEN);
        pageContentStream.setFont( PDType1Font.TIMES_ITALIC, 26 );

        for (int i = 1; i < speech.size(); i++) {
            write(speech.get(i), pageContentStream, 700 - (i * 50), Color.black);
        }

        pageContentStream.close();
        document.save("./PDF_Tickets/Ticket.pdf");
        return document;
    }

    private void write(String text, PDPageContentStream pageContentStream, int ty, Color color) throws IOException {
        pageContentStream.beginText();
        pageContentStream.setNonStrokingColor(color);
        pageContentStream.newLineAtOffset(110, ty);
        pageContentStream.showText(text);
        pageContentStream.endText();
    }
}
