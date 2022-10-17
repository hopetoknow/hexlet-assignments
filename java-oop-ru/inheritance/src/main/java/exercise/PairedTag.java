package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = Objects.isNull(children) ? new ArrayList<>() : children;
    }

    public String childrenToString() {
        return children.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return String.format("<%s%s>%s%s</%s>", getName(), attributesToString(), childrenToString(),
                body, getName());
    }
}
// END
