import javax.swing.*;
import java.awt.*;

public abstract class Nemico extends Personaggio {
    boolean existing;
    public abstract void ballCollision();
    public abstract void movimento();
    public abstract void shoot();
    public void setExisting(boolean existing) {
        this.existing = existing;
    }
    public void shootCollision(){
        for (GameObject go : lgo) {
            if (go != this) {
                if(go instanceof Fuoco){
                    if(((Fuoco) go).getBounds().intersects(this.getBounds())){
                        setExisting(false);
                    }
                }
            }
        }
    }
}

