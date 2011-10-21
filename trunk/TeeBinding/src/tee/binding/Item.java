package tee.binding;

public class Item {

    public Column column;
    public Row row;
    public String value;

    public Item(Row r, Column c, String v) {
	row = r;
	column = c;
	value = v;
    }
}
