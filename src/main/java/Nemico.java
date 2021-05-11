import javax.swing.*;
import java.awt.*;

public abstract class Nemico extends Personaggio {

    public abstract void ballCollision();
    public abstract void movimento();
    public abstract void shoot();
}

