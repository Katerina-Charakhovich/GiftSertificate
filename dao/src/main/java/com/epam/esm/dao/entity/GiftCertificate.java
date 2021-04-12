package com.epam.esm.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GiftCertificate extends Entity {

    private long id;
    private String name;
    private String description;
    private int duration;
    private BigDecimal price;
    LocalDateTime lastUpdateDate;
    LocalDateTime createDate;
    List<Tag> listTag;

    public GiftCertificate() {
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<Tag> getListTag() {
        return listTag;
    }

    public void setListTag(List<Tag> listTag) {
        this.listTag = listTag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return id == that.id &&
                duration == that.duration &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                lastUpdateDate.equals(that.lastUpdateDate) &&
                createDate.equals(that.createDate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = (int) (id ^ (id >>> 32));
        result = result * prime + Integer.hashCode(duration);
        result = result * prime + name.hashCode();
        result = result * prime + description.hashCode();
        result = result * prime + price.hashCode();
        result = result * prime + lastUpdateDate.hashCode();
        result = result * prime + createDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder strResult = new StringBuilder("Certificate {");
        strResult.append("id=").append(id).append(';');
        strResult.append(", duration=").append(duration);
        strResult.append(", name=").append(name);
        strResult.append(", description=").append(description);
        strResult.append(", price=").append(price);
        strResult.append(", lastUpdateDate=").append(lastUpdateDate);
        strResult.append(", createDate=").append(createDate).append('}');
        return strResult.toString();
    }
}
