package tee.binding;

import tee.binding.properties.*;
import tee.binding.it.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

/**
 *
 * @author User
 */
public class Bough {

    public Bough parent = null;
    public NoteProperty<Bough> name = new NoteProperty(this);
    public NoteProperty<Bough> value = new NoteProperty(this);
    private Vector<Bough> children = new Vector<Bough>();
    public ToggleProperty<Bough> attribute = new ToggleProperty<Bough>(this);

    public Bough child(Bough b) {
        children.add(b);
        b.parent = this;
        return this;
    }

    public Vector<Bough> children() {
        return children;
    }

    public Bough child(String n) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.property.value().equals(n)) {
                return children.get(i);
            }
        }
        Bough b = new Bough().name.is(n);
        child(b);
        return b;
    }

    public Vector<Bough> select(String n) {
        Vector<Bough> c = new Vector<Bough>();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.property.value().equals(n)) {
                c.add(children.get(i));
            }
        }
        return c;
    }

    public static String safeEncodedString(String s) {
        s = s.replaceAll("\"", "&quot;");
        return s;
    }

    public static void dumpXML(StringBuilder sb, Bough b, String pad) {
        sb.append("\n" + pad + "<" + b.name.property.value());
        for (int i = 0; i < b.children.size(); i++) {
            if (b.children.get(i).attribute.property.value()) {
                sb.append(" " + b.children.get(i).name.property.value() //
                        + "=\"" + safeEncodedString(b.children.get(i).value.property.value()) + "\"");
            }
        }
        sb.append(">" + safeEncodedString(b.value.property.value()));
        boolean hasChildren = false;
        for (int i = 0; i < b.children.size(); i++) {
            if (!b.children.get(i).attribute.property.value()) {
                hasChildren = true;
                dumpXML(sb, b.children.get(i), "\t" + pad);
            }
        }
        if (hasChildren) {
            sb.append("\n" + pad + "</" + b.name.property.value() + ">");
        } else {
            sb.append("</" + b.name.property.value() + ">");
        }
    }

    public static Bough parseXML(String data) {
        final It<Bough> current = new It<Bough>().value(new Bough());

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    Bough b = new Bough().name.is(qName);
                    if (current.value() != null) {
                        current.value().child(b);
                    }
                    current.value(b);
                    for (int i = 0; i < attributes.getLength(); i++) {
                        current.value().child(new Bough()//
                                .name.is(attributes.getQName(i))//
                                .value.is(attributes.getValue(i))//
                                .attribute.is(true)//
                                );
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    String c = current.value().value.property.value();
                    current.value().value.is(c.trim());
                    current.value(current.value().parent);
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    String c = current.value().value.property.value();
                    current.value().value.is(c + new String(ch, start, length));
                }
            };
            InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
            saxParser.parse(is, handler);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return current.value().children().get(0);
    }

    public static void main(String[] a) {
        Bough b = new Bough().name.is("Test").value.is("Ops");
        b.child("test").child("test2").child("test3").attribute.is(true).value.is("Ya!");
        b.child("test").child("test4").value.is("Nope!");
        b.child("test").child(new Bough().name.is("test5").value.is("Yet!"));
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        dumpXML(sb, b, "");
        String xml = sb.toString();
        System.out.println("created tree");
        System.out.println(xml);
        sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        dumpXML(sb, parseXML(xml), "");
        System.out.println("parsed tree");
        System.out.println(sb.toString());
        /*
        sb = new StringBuilder();
        String testPath = "D:\\testdata\\AndroidExchange_12-_HRC01_0000000072.xml";
        java.io.File file = new java.io.File(testPath);
        try {
            java.io.InputStream in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            int len = bytes.length;
            int total = 0;
            while (total < len) {
                int result = in.read(bytes, total, len - total);
                if (result == -1) {
                    break;
                }
                total = total + result;
            }
            String dat = new String(bytes);

            Bough testXML = Bough.parseXML(dat);
            dumpXML(sb, testXML, "");
            System.out.println("big tree");
            System.out.println(sb.toString());
        } catch (Throwable tt) {
            tt.printStackTrace();
        }
        */
    }
}
