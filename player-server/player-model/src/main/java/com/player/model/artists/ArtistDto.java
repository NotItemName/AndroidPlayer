package com.player.model.artists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Mykola_Zalyayev
 */
@JsonRootName("artist")
public class ArtistDto {

    private Integer id;

    private String name;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArtistDto artistDto = (ArtistDto) o;

        if (id != null ? !id.equals(artistDto.id) : artistDto.id != null) {
            return false;
        }
        if (name != null ? !name.equals(artistDto.name) : artistDto.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArtistDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
