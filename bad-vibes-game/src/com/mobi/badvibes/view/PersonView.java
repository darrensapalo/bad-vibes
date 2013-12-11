package com.mobi.badvibes.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Cubic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.mobi.badvibes.BadVibes;
import com.mobi.badvibes.Point;
import com.mobi.badvibes.model.people.Person;
import com.mobi.badvibes.model.world.World;
import com.mobi.badvibes.nimators.PersonAccessor;
import com.mobi.badvibes.util.MathHelper;

public class PersonView implements Poolable
{
    /*
     * Static variables
     */

    /**
     * Walking forward means facing the train, walking backward means facing
     * away from the train, idle means not moving, and picked up means held like
     * a kitten.
     * 
     * @author Darren
     * 
     */
    public enum State
    {
        IDLE, WALKING, PICKED_UP
    }

    public enum Facing
    {
        FORWARD, BACKWARD
    }

    public enum Emotions
    {
        NONE, HAPPY, NAUGHTY, ANGRY
    }

    public enum Character
    {
        NORMAN_THE_NORMAL, PATRICK_THE_PUSHER, CARL_THE_COUNTERFLOWER
    }

    private static final int                FRAME_COLS     = 4;
    private static final int                FRAME_ROWS     = 4;

    public static final float               WIDTH          = 52;
    public static final float               HEIGHT         = 83;

    public static final float               SHADOW_WIDTH   = 52;
    public static final float               SHADOW_HEIGHT  = 9;

    public static final int                 EMOTION_WIDTH  = 45;
    public static final int                 EMOTION_HEIGHT = 36;

    protected static ArrayList<PersonEntry> NormanTheNormal;

    private static BitmapFont               defaultFont;

    private static Random                   random         = new Random();

    /*
     * Attributes
     */
    protected Person                        person;

    protected Animation                     animationIdleLookingBackward;
    protected Animation                     animationIdleLookingForward;

    protected Animation                     animationIdleBackward;
    protected Animation                     animationIdleForward;

    protected Animation                     animationWalkingForward;
    protected Animation                     animationWalkingBackward;

    protected Animation                     animationPickedUp;

    protected Animation                     currentAnimation;

    protected Vector2                       Position;
    protected Vector2                       pickupOffset;
    protected Rectangle                     Bounds;
    protected Rectangle                     HitBounds;
    
    protected State                         currentState;
    protected Facing                        currentFacing;
    protected Emotions                      currentEmotion;

    protected int                           currentBucketID;
    protected float                         stateTime;
    protected float                         opacity;

    private TextureRegion[][]               emotions;

    private float                           emotionOpacity;

    private Color                           tint;

    private Point                           pickupCell;

    public static void Initialize()
    {
        defaultFont = new BitmapFont(Gdx.files.internal("data/Arial20.fnt"), Gdx.files.internal("data/Arial20.png"), true);
        defaultFont.setColor(0f, 0f, 0f, 1f);

        NormanTheNormal = new ArrayList<PersonEntry>();

        for (int i = 0; i < 15; i++)
        {
            PersonEntry newView     = setPersonView();
            
            NormanTheNormal.add(newView);
        }
    }

    public static PersonView getView(Character character)
    {
        switch (character)
        {
        case NORMAN_THE_NORMAL:

            for (int i = 0; i < NormanTheNormal.size(); i++)
            {
                PersonEntry view = NormanTheNormal.get(i);

                if (view.taken == false)
                {
                    view.taken = true;
                    return view.view;
                }
            }

            PersonEntry newView     = setPersonView();
                        newView.taken = true;

            NormanTheNormal.add(newView);

            return newView.view;

        default:
            return null;
        }
    }

    public static void releasePerson(Character character, PersonView view)
    {
        switch (character)
        {
        case NORMAN_THE_NORMAL:

            for (int i = 0; i < NormanTheNormal.size(); i++)
            {
                PersonEntry entry = NormanTheNormal.get(i);

                if (view == entry.view)
                {
                    entry.taken = false;
                }
            }
            break;
        case CARL_THE_COUNTERFLOWER:
            break;
        case PATRICK_THE_PUSHER:
            break;
        default:
            break;
        }
    }

    private static PersonEntry setPersonView()
    {
        int      randomPick  = Math.abs(random.nextInt());

        System.out.println(randomPick);
        
        Calendar cal = Calendar.getInstance();
        
        if      (randomPick % 18 == 0 || (cal.get(Calendar.MONTH) == 8 && cal.get(Calendar.DATE) == 1 && randomPick % 5 == 0))
        {
            return new PersonEntry(Load("data/game/personcharlene.png"));
        }
        else if (randomPick % 15 == 0)
        {
            return new PersonEntry(Load("data/game/person4.png"));
        }
        else if (randomPick % 12 == 0)
        {
            return new PersonEntry(Load("data/game/person10.png"));
        }
        else if (randomPick % 10 == 0)
        {
            return new PersonEntry(Load("data/game/person9.png"));
        }
        else if (randomPick % 9 == 0)
        {
            return new PersonEntry(Load("data/game/person8.png"));
        }
        else if (randomPick % 8 == 0)
        {
            return new PersonEntry(Load("data/game/person7.png"));
        }
        else if (randomPick % 7 == 0)
        {
            return new PersonEntry(Load("data/game/person6.png"));
        }
        else if (randomPick % 6 == 0)
        {
            return new PersonEntry(Load("data/game/person5.png"));
        }
        else if (randomPick % 4 == 0)
        {
            return new PersonEntry(Load("data/game/person3.png"));
        }
        else if (randomPick % 3 == 0)
        {
            return new PersonEntry(Load("data/game/person2.png"));
        }
        else
        {
            return new PersonEntry(Load("data/game/person1.png"));
        }
    }
    
    protected static PersonView Load(String path)
    {
        return new PersonView(0.15f, path);
    }

    /**
     * The constructor of this object is private, so that it can only be created
     * in this Factory class (PersonView)
     * 
     * @param frameDuration
     * @param path
     */
    private PersonView(float frameDuration, String path)
    {
        Texture texture = new Texture(Gdx.files.internal(path));

        TextureRegion[][] region = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);

        for (int y = 0; y < region.length; y++)
        {
            for (int x = 0; x < region[y].length; x++)
            {
                region[y][x].flip(false, true);
            }
        }

        animationIdleLookingForward = new Animation(frameDuration, region[0]);
        animationIdleForward = new Animation(frameDuration, region[0][0]);
        animationWalkingForward = new Animation(frameDuration, region[1]);
        animationWalkingBackward = new Animation(frameDuration, region[2]);
        animationPickedUp = new Animation(frameDuration, region[3][0]);

        /** Load the emotions properly **/
        Texture emoticonSheet = new Texture(Gdx.files.internal("data/game/emotions.png"));
        emotions = TextureRegion.split(emoticonSheet, EMOTION_WIDTH, EMOTION_HEIGHT);

        for (int y = 0; y < emotions.length; y++)
        {
            for (int x = 0; x < emotions[y].length; x++)
            {
                emotions[y][x].flip(false, true);
            }
        }
        reset();
    }

    /**
     * Determines which animation to use, whether forward or backward depending
     * on the y axis of the destination.
     * 
     * @param destination
     *            - the next destination of the player
     */
    public void setDestination(Vector2 destination)
    {
        setCurrentState(State.WALKING);
        if (getPosition().y < destination.y)
            setCurrentFacing(Facing.BACKWARD);
        else
            setCurrentFacing(Facing.FORWARD);
    }

    // Getters and setters

    public void setPosition(Vector2 position)
    {
//    	Vector2 _Position = Position;
//    	Rectangle _Bounds = Bounds;
//    	Rectangle _HitBounds = HitBounds;
    	
        Position = position;
        Bounds = new Rectangle(position.x + (GameDimension.Cell.x - GameDimension.Person.x) / 2.0f, position.y - (GameDimension.Cell.y), GameDimension.Person.x, GameDimension.Person.y);
        HitBounds = new Rectangle(Bounds);
        float newWidth = HitBounds.getWidth() * 0.6F;
        float newHeight = HitBounds.getHeight() * 0.4F;
        
        float diffWidth = HitBounds.getWidth() - newWidth;
        float diffHeight = HitBounds.getHeight() - newHeight;
        
        HitBounds.setWidth(newWidth);
        HitBounds.setHeight(newHeight);
        HitBounds.x += diffWidth / 2;
        HitBounds.y += diffHeight * 2 / 3;
//        
//        boolean shouldRollBack = false;
//        for (Person p : World.Instance.getPeopleList()) {
//			if (p.equals(person)) continue;
//			if (p.intersects(person)){
//				shouldRollBack = true;
//				break;
//			}
//		}
//        /* Almost intersected with a person! Stop. */
//        if (shouldRollBack){
//        	Position = _Position;
//        	Bounds = _Bounds;
//        	HitBounds = _HitBounds;
//        	person.setLogic(new PauseLogic(person, null, 0.1f, Emotions.NONE));
//        	return;
//        }
//        
        
        if (WorldRenderer.Instance != null)
        {
            int bucketID = computeBucketID(position);
        	
            if (currentBucketID != bucketID){
            	currentBucketID = bucketID;
            	WorldRenderer.Instance.addToList(this, bucketID);
            }
        }
    }

    private int computeBucketID(Vector2 position)
    {
        float finalPosition = position.y - GameDimension.PlatformOffset + GameDimension.Cell.y / 2;
        
        // from -1 for the people in the train
        int clamp = MathHelper.Clamp((int) finalPosition / (int) GameDimension.MiniCell.y, 0, World.GRID_HEIGHT - 1);
        return clamp;
    }

    public Animation getAnimation()
    {
        return animationIdleForward;
    }

    public void render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta)
    {
        stateTime += delta;
        currentAnimation = getCurrentAnimation();

        TextureRegion region = currentAnimation.getKeyFrame(stateTime, true);
        TextureRegion emotionRegion = getCurrentEmotion(currentEmotion);
        Vector2 computedPosition = getComputedPosition();
        Vector2 emotionPosition = getEmotionPosition();

        spriteBatch.begin();

        /** Draw the shadow **/

        // Marit added shadows in the revised persons.... hrm...
        // Vector2 shadowPosition = getShadowPosition();
        // spriteBatch.setColor(1f, 1f, 1f, ((currentState == State.PICKED_UP) ? 0.3f : 0.5f));
        // spriteBatch.draw(shadowfeet, shadowPosition.x, shadowPosition.y,
        // GameDimension.Shadow.x, GameDimension.Shadow.y);

        /** Draw the person **/
        if (tint == null)
            spriteBatch.setColor(1f, 1f, ((currentState == State.PICKED_UP) ? 0.5f : 1.0f), opacity);
        else
            spriteBatch.setColor(tint);
        spriteBatch.draw(region, computedPosition.x, computedPosition.y, 0, 0, GameDimension.Person.x, GameDimension.Person.y, 1.0f, 1.0f, 0f);

        /** Draw the emotion **/
        spriteBatch.setColor(1f, 1f, 1f, emotionOpacity);

        if (emotionRegion != null)
            spriteBatch.draw(emotionRegion, emotionPosition.x, emotionPosition.y, 0, 0, GameDimension.Emotions.x, GameDimension.Emotions.y, 1.0f, 1.0f, 0f);

        spriteBatch.end();
    }

    public void setEmotion(Person person, Emotions e)
    {
    	currentEmotion = e;
        Timeline.createSequence().push(Tween.to(person, PersonAccessor.EMOTION_OPACITY, 0.3f).target(1f).ease(Cubic.INOUT)).push(Tween.to(person, PersonAccessor.EMOTION_OPACITY, 0.3f).target(0f).ease(Cubic.INOUT).delay(1f)).start(BadVibes.tweenManager);
    }

    public Vector2 getEmotionPosition()
    {
        return Position.cpy().add((GameDimension.Cell.x - GameDimension.Emotions.x) / 2.0f, 0).sub(0, GameDimension.Cell.y + GameDimension.Emotions.y).add(pickupOffset);
    }

    public Vector2 getComputedPosition()
    {
        return Position.cpy().add((GameDimension.Cell.x - GameDimension.Person.x) / 2.0f, 0).sub(0, GameDimension.Cell.y).add(pickupOffset);
    }

    /*
    private Vector2 getShadowPosition()
    {
        return Position.cpy().add((GameDimension.Cell.x - GameDimension.Shadow.x) / 2.0f, 0).add(0, GameDimension.Cell.y * 0.95f);
    }
    */
    private TextureRegion getCurrentEmotion(Emotions e)
    {
        switch (e)
        {
        case HAPPY:
            return emotions[0][0];
        case NAUGHTY:
            return emotions[0][1];
        case ANGRY:
            return emotions[0][2];
        default:
            return null;
        }
    }

    private Animation getCurrentAnimation()
    {
        if (currentState == State.PICKED_UP)
            return animationPickedUp;
        if (currentFacing == Facing.FORWARD)
        {
            switch (currentState)
            {
            case WALKING:
                return animationWalkingForward;
            default:
            case IDLE:
                return animationIdleForward;
            }
        }
        else
        {
            switch (currentState)
            {
            case WALKING:
                return animationWalkingBackward;
            default:
            case IDLE:
                // TODO: This needs to be replaced to animationIdleBackwards
                // once the art is done
                return animationIdleForward;
            }
        }
    }

    public State getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(State currentState)
    {
        this.currentState = currentState;
    }

    public Rectangle getBounds()
    {
        return Bounds;
    }

    public float getOpacity()
    {
        return this.opacity;
    }

    public void setOpacity(float opacity)
    {
        this.opacity = opacity;
    }

    public Vector2 getPosition()
    {
        return Position;
    }

    public Vector2 getPickupOffset()
    {
        return pickupOffset;
    }

    public void setPickupOffset(Vector2 pickupOffset)
    {
        this.pickupOffset = pickupOffset;
    }

    public Facing getCurrentFacing()
    {
        return currentFacing;
    }

    public void setCurrentFacing(Facing currentFacing)
    {
        this.currentFacing = currentFacing;
    }

    public float getEmotionOpacity()
    {
        return emotionOpacity;
    }

    public void setEmotionOpacity(float emotionOpacity)
    {
        this.emotionOpacity = emotionOpacity;
    }

	public int getCurrentBucketID() {
		return currentBucketID;
	}

    public Rectangle getHitBounds() {
        return HitBounds;
    }

    public void setHitBounds(Rectangle hitBounds) {
        HitBounds = hitBounds;
    }

	public void setColor(Color red) {
		tint = red;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPickupCell(Point platformPoint) {
		this.pickupCell = platformPoint;
	}

	public Point getPickupCell() {
		return pickupCell;
	}

    @Override
    public void reset()
    {
        currentEmotion = Emotions.HAPPY;

        setCurrentState(State.IDLE);
        setCurrentFacing(Facing.FORWARD);

        currentBucketID = -1;
        opacity = 1f;

        setPickupOffset(Vector2.Zero);        
    }

}
