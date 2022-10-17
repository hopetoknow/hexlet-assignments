package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String tagBody;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String tagBody, List<Tag> children) {
        super(tagName, tagAttributes);
        this.tagBody = tagBody;
        this.children = Objects.isNull(children) ? new ArrayList<>() : children;
    }

    public String childrenToString() {
        return children.stream()
                .map(tag -> {
                    SingleTag singleTag = (SingleTag) tag;
                    return singleTag.toString();
                })
                .collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return String.format("<%s%s>%s%s</%s>", getTagName(), attributesToString(), childrenToString(),
                tagBody, getTagName());
    }
}
// END
