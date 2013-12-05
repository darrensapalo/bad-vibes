package com.mobi.badvibes.model.people;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.controller.gameplay.DragGameplay.DragState;
import com.mobi.badvibes.model.people.logic.PersonLogic;
import com.mobi.badvibes.model.people.logic.StillLogic;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.util.GameUtil;
import com.mobi.badvibes.view.PersonView;
import com.mobi.badvibes.view.PersonView.Emotions;
import com.mobi.badvibes.view.PersonView.State;

/**
 * This class contains the logic and the view of a Person class.
 * 
 * @see PersonView
 * @see PersonLogic
 * 
 * @author Darren
 * 
 */
public abstract class Person
{
    public static final int   MAX_IDLE_TIME = 10;
    public static final int   MIN_IDLE_TIME = 5;

    public static final float VELOCITY      = 0.5f;

    /**
     * This value shows how responsive the person is when picked up with the
     * drag-and-drop gesture. It is set to 2 by default. The lowest value is 1.
     * The heaviest weight is 16.
     */
    protected float           weight        = 2;

    /**
     * This value ranges between 0 to 1, which represents how much patience he
     * has spent. At the value of 1, the person is permanently displeased and
     * happiness drops to flat -1. It is set to 0 by default.
     */

    protected float           tolerance     = 0;
    /**
     * This value ranges between -1 to 1, which signifies whether a person is
     * extremely unhappy or very pleased with the experience. It is set to 0 by
     * default.
     */
    protected float           happiness     = 0;

    /**
     * This attribute handles the displaying of the image.
     */
    protected PersonView      view;

    /**
     * This attribute determines the logic that the person follows. There are
     * multiple kinds of logic such as ArrivedLogic, BoardingLogic, ArguingLogic
     */
    protected PersonLogic     logic;

    protected Point           destinationCell;

    private World             parent;

    public Tween              walkingTween;
    
    
    /* Touch / drag variables */
    public int                touchID;
    
    public DragState          state;
    
    public Vector2             startPoint, offset;

    /**
     * Constructor that requires the logic and the view of the person
     * 
     * @param logic
     * @param view
     */
    public Person(PersonView view)
    {
        this.view = view;
        this.view.setPerson(this);
        this.destinationCell = Point.Negative;
        this.touchID = -1;
        this.state = DragState.Free;
    }

    /**
     * This method is called to allow the person to update as necessary
     * depending on what kind of person it is.
     * 
     * @param delta
     *            - how much time has changed since last update
     */
    public void preupdate(float delta)
    {
        if (logic != null)
            logic.think(delta);

        this.update(delta);
    }

    public void initialize(World theWorld, boolean inTrain)
    {
        parent = theWorld;

        if (inTrain)
        {
            // TODO: update value to match the train's door.
            getView().setPosition(theWorld.getTrain().trainView.Position);
            // we don't set a logic for them yet.
            view.setColor(Color.RED);
            return;
        }


        // Find n
        Point n = theWorld.getRandomCellCoordinate();
        Vector2 personLocation = GameUtil.getPlatformVectorCentered(n);

        getView().setPosition(personLocation);
        getView().setCurrentState(State.WALKING);

        setDestinationCell(n);
        setLogic(new StillLogic(this));
    }

    public abstract void update(float delta);

    public World getWorld()
    {
        return parent;
    }

    public float getWeight()
    {
        return weight;
    }

    public float getTolerance()
    {
        return tolerance;
    }

    public float getHappiness()
    {
        return happiness;
    }

    public Point getDestinationCell()
    {
        return destinationCell;
    }

    public PersonView getView()
    {
        return view;
    }

    public PersonLogic getLogic()
    {
        return logic;
    }

    public void setLogic(PersonLogic logic)
    {
        this.logic = logic;
    }

    public void setDestinationCell(Point newPoint)
    {
        destinationCell = newPoint;
    }
    
    public Point getCurrentCell(){
    	return view.getCellLocation();
    }

	public void displease() {
		happiness -= 0.02f;
		view.setEmotion(this, Emotions.ANGRY);
	}
	
	public String toString(){
		return "Person " + hashCode();
	}
	
	public boolean intersects(Person p){
		if (view.getHitBounds() == null) return false;
		if (p.view.getHitBounds() == null) return false;
		return view.getHitBounds().overlaps(p.view.getHitBounds());
	}
}
