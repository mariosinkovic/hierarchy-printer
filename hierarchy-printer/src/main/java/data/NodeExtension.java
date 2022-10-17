package data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class NodeExtension implements Serializable {

    private String parent;
    private NodeExtension[] nodeExtensions; // this could be a set<> maybe to ensure unique data

    public NodeExtension(){};
    public NodeExtension(String parent, NodeExtension[] nodeExtensions) {
        this.parent = parent;
        this.nodeExtensions = nodeExtensions;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public NodeExtension[] getNodeExtensions() {
        return nodeExtensions;
    }

    public void setNodeExtensions(NodeExtension[] nodeExtensions) {
        this.nodeExtensions = nodeExtensions;
    }

    @Override
    public String toString() {
        // for printing data.NodeExtension only this is what matters;
        return "data.NodeExtension{" +
                "parent='" + parent + '\'' +
                ", nodeExtensions=" + Arrays.toString(nodeExtensions) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeExtension that = (NodeExtension) o;
        // for comparing data.NodeExtension only this is what matters;
        return Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(parent);
        // for comparing data.NodeExtension only this is what matters;
        result = 31 * result;
        return result;
    }
}
