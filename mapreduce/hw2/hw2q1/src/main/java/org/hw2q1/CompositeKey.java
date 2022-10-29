package org.hw2q1;

import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import java.io.DataInput;
import java.io.DataOutput;

public class CompositeKey implements WritableComparable<CompositeKey> {
    private String zipcode;
    private char sex;

    public CompositeKey() {
        zipcode = new String();
        sex = ' ';
    }

    public CompositeKey(String zipcode, char sex) {
        this.zipcode = zipcode;
        this.sex = sex;
    }

    public char getSex() {
        return sex;
    }

    public void write(DataOutput out) throws IOException {
        WritableUtils.writeString(out, zipcode);
        out.writeChar(sex);
    }

    public void readFields(DataInput in) throws IOException {
        zipcode = WritableUtils.readString(in);
        sex = in.readChar();
    }

    public int compareTo(CompositeKey o) {
        return zipcode.compareTo(o.zipcode);
    }

    @Override
    public String toString() {
        return "(" + zipcode + "," + sex + ")";
    }
    // public int hashCode() {
    // final int prime = 31;
    // String str = zipcode + sex;
    // Integer i = sex % 8;
    // int result = 1;
    // result = prime * result + str.hashCode();
    // result = prime * result + (int) (i ^ (i >>> 32));
    // return result;
    // }
}
