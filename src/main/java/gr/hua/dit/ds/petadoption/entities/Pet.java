package gr.hua.dit.ds.petadoption.entities;

public class Pet {

    private Integer Id;


    private String name;

    private String type;

    private String status;



    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Pet() {}

    public Pet(Integer id, String name, String type, String status) {
        Id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}