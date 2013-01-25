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

    public Bough p = null;
    public boolean a = false;
    public NoteProperty<Bough> name = new NoteProperty(this);
    public NoteProperty<Bough> value = new NoteProperty(this);
    public Vector<Bough> children = new Vector<Bough>();
    public ToggleProperty<Bough> attribute = new ToggleProperty<Bough>(this);

    public Bough child(Bough b) {
        children.add(b);
        //b.parent = this;
        return this;
    }
    /*
     public Vector<Bough> children() {
     return children;
     }*/

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

    public Vector<Bough> children(String n) {
        Vector<Bough> c = new Vector<Bough>();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).name.property.value().equals(n)) {
                c.add(children.get(i));
            }
        }
        return c;
    }

    public static String safeEncodedString(String s) {
        if (s == null) {
            return "";
        }
        s = s.replaceAll("\"", "&quot;");
        return s;
    }

    public String dumpXML() {
        StringBuilder sb = new StringBuilder();
        Bough.dumpXML(sb, this, "");
        return sb.toString();
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

    public static String readTillChar(java.io.StringReader reader, char stop) throws Exception {
        String s = "";
        char c = 0;
        int i = reader.read();
        while (i != -1) {
            c = (char) i;
            if (c == stop) {
                break;
            } else {
                s = s + c;
                i = reader.read();
            }
        }
        return s;
    }

    public static String pad(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + ".";
        }
        return s;
    }

    public static Bough parseJSON(String data) {
        Bough current = null;
        Vector<String> tokens = new Vector<String>();
        try {
            java.io.StringReader reader = new java.io.StringReader(data);
            char c = 0;
            String s = "";
            int i = reader.read();
            while (i != -1) {
                c = (char) i;
                if (c == '"') {
                    tokens.add(readTillChar(reader, '"'));
                } else {
                    if (c == '{' || c == '}' || c == '[' || c == ']' || c == ',' || c == ':') {
                        if (tokens.size() > 0) {
                            if (tokens.get(tokens.size() - 1).equals("/delimiter:")) {
                                tokens.add(s.trim());
                            }
                        }
                        tokens.add("/delimiter" + c);
                        if (c == ':') {
                            s = "";
                        }
                    } else {
                        s = s + c;
                    }
                }
                i = reader.read();
            }
            for (int n = 0; n < tokens.size(); n++) {
                if (tokens.get(n).equals("/delimiter{")) {
                    String name = tokens.get(n + 1);
                    Bough b = new Bough().name.is(name);
                    if (current != null) {
                        b.p = current;
                        current.child(b);
                    }
                    current = b;
                    String value = tokens.get(n + 3);
                    if (!(value.equals("/delimiter{") || value.equals("/delimiter["))) {
                        current.value.is(value);
                    }
                } else {
                    if (tokens.get(n).equals("/delimiter,")) {
                        if (current.a) {
                            Bough b = new Bough().name.is(current.name.property.value());
                            b.p = current.p;
                            current.p.child(b);
                            current = b;
                            current.a = true;
                            String value = tokens.get(n + 1);
                            if (value.equals("/delimiter{")) {
                                //
                            } else {
                                current.value.is(value);
                            }
                        } else {
                            String name = tokens.get(n + 1);

                            Bough b = new Bough().name.is(name);
                            b.p = current.p;
                            current.p.child(b);
                            current = b;
                            String value = tokens.get(n + 3);
                            if (!(value.equals("/delimiter{") || value.equals("/delimiter["))) {
                                current.value.is(value);
                            }
                        }
                    } else {
                        if (tokens.get(n).equals("/delimiter}")) {
                            if (current.p != null) {
                                current = current.p;
                            }
                        } else {
                            if (tokens.get(n).equals("/delimiter[")) {
                                String value = tokens.get(n + 1);
                                if (!(value.equals("/delimiter{") || value.equals("/delimiter["))) {
                                    current.value.is(value);
                                }
                                current.a = true;
                            } else {
                                if (tokens.get(n).equals("/delimiter]")) {
                                    current.a = false;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return current;
    }

    public static Bough parseXML(String data) {
        final It<Bough> current = new It<Bough>().value(new Bough());
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    Bough b = new Bough().name.is(qName);
                    if (current.value() != null) {
                        current.value().value.is(stringBuilder.toString().trim());
                        b.p = current.value();
                        current.value().child(b);
                    }
                    stringBuilder.delete(0, stringBuilder.length());
                    current.value(b);
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Bough bb = new Bough()//
                                .name.is(attributes.getQName(i))//
                                .value.is(attributes.getValue(i))//
                                .attribute.is(true);
                        bb.p = current.value();
                        current.value().child(bb);
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //String c = current.value().value.property.value();
                    //current.value().value.is(c.trim());
                    current.value().value.is(stringBuilder.toString().trim());
                    current.value(current.value().p);
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(current.value().value.property.value());
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    //String c = current.value().value.property.value();
                    //current.value().value.is(c + new String(ch, start, length));
                    //System.out.println("-----------"+ch.length);
                    stringBuilder.append(ch, start, length);
                }
            };
            InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));
            saxParser.parse(is, handler);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return current.value().children.get(0);
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
        Bough b2 = Bough.parseXML(xml);
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


        /*
         sb = new StringBuilder();
         String testPath = "D:\\testdata\\test.json";
         java.io.File file = new java.io.File(testPath);
         String dat = "";
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
         dat = new String(bytes);
         System.out.println("json parse");
         Bough testXML = Bough.parseJSON(dat);
         dumpXML(sb, testXML, "");
         System.out.println("json tree");
         System.out.println(sb.toString());

         } catch (Throwable tt) {
         tt.printStackTrace();
         }

         */
    }
}
