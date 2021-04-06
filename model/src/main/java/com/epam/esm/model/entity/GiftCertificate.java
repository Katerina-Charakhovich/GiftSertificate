package com.epam.esm.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiftCertificate extends Entity {

    private long id;

    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]+$")
    private String name;

    @Size(min = 3, max = 255)
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]+$")
    private String description;

    @Max(10)
    @Min(1)
    private int duration;

    @DecimalMin("1")
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime lastUpdateDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
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
