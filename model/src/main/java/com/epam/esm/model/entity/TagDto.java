package com.epam.esm.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDto extends Dto {
    @JsonView(ViewProfileJackson.GetRecourse.class)
    private long tagId;
    @JsonView(ViewProfileJackson.UpdateAndCreateRecourse.class)
    @NotBlank
    @Size(min = 2, max = 3, message = "Incorrect length ")
    private String tagName;

    public TagDto() {
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDto tagDto = (TagDto) o;
        return tagId == tagDto.tagId &&
                tagName.equals(tagDto.tagName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = (int) (tagId ^ (tagId >>> 32));
        result = result * prime + tagName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder strResult = new StringBuilder("Tag {");
        strResult.append("tagId=").append(tagId).append(';');
        strResult.append(", tagName=").append(tagName).append('}');
        return strResult.toString();
    }
}
