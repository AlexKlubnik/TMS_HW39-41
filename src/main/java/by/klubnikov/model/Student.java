package by.klubnikov.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Student {

    private int id;

    @NotNull
    @Size(min=2, max=30, message = "Name should have more then {min} letters and be less than {max}")
    private String name;

    @NotNull
    @Size(min = 2, max=200)
    private String address;

    public Student() {}
}
