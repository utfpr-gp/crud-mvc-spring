package br.edu.utfpr.crudmvcspring.util.tabs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabDTO {
    private String name;
    private String route;
}
