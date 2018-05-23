package model.staff;

/**Абстрактный класс для "Элемент организационной структуры"
 * @author nsychev
 * @since 22.05.2018
 */
public abstract class Staff {
    /**Идентификатор*/
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
