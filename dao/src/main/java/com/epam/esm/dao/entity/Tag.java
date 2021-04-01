package com.epam.esm.dao.entity;

public class Tag extends Entity {
    private long tagId;
    private String tagName;

    public Tag() {
    }

    public long getId() {
        return tagId;
    }

    public void setId(long id) {
        this.tagId = id;
    }

    public String getName() {
        return tagName;
    }

    public void setName(String name) {
        this.tagName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId == tag.tagId &&
                tagName.equals(tag.tagName);
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
        strResult.append("{tagId=").append(tagId).append(';');
        strResult.append(", tagName=").append(tagName).append('}');
        return strResult.toString();
    }
}
