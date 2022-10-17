package data;

import java.util.Objects;

public class ParentChildPair {
    private String parent;
    private String child;

    public ParentChildPair(){};
    public ParentChildPair(String parent, String child) {
        this.parent = parent;
        this.child = child;
    }

    public String getParent() {
        return parent;
    }

    public String getChild() {
        return child;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "data.ParentChildPair{" +
                "parent='" + parent + '\'' +
                ", child='" + child + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentChildPair that = (ParentChildPair) o;
        return Objects.equals(parent, that.parent) && Objects.equals(child, that.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, child);
    }

}
