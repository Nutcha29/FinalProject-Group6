/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.units;

import javax.swing.JLabel;

import example.User;

import model.core.DoublePoint;
import model.core.Square;
import model.core.events.MoveEvent;
import model.core.events.SelectEvent;
import model.core.moveable.Moveable;
import model.core.texture.MoveableTextureCollection;
import view.EngineWindow;

/**
 *
 * @author Wouter
 */
public class IceTizen extends Moveable {

  /**
   * Creates a Truck.
   * @param pixelLocation Location to create the Truck at.
   * @param speed Speed
   * @param collections Textures
   * @see Moveable
   */
	//private JLabel nameTag;
  public IceTizen(DoublePoint pixelLocation, int speed, MoveableTextureCollection textures) {
    this.setPixelLocation(pixelLocation);
    this.setSquare(EngineWindow.getInstance().getGame().getPlayfieldGrid().
            translatePixelToSquare(pixelLocation.toPoint()));
    this.setSpeed(speed);
    this.setMoveableTextureCollection(textures);
    //this.setName(n);
  }
/*
  public void setName(String s){
	  this.nameTag.setText(s);
  }
  */
  @Override
  public String toString() {
    return "Truck @ ( " + this.getSquare() + " )";
  }

  @Override
  public void onMove(MoveEvent event) {
    //if (this.isOnFire()) {
    switch (event.getType()) {
      case MoveEvent.TYPE_MOVE:
        break;
      case MoveEvent.TYPE_MOVE_STARTED:
        break;
      case MoveEvent.TYPE_MOVE_ENDED:
        break;
    }
  }

  @Override
  public void onSelect(SelectEvent event) {
    // System.out.println(event);
    // throw new UnsupportedOperationException("Not supported yet.");
  }
}
