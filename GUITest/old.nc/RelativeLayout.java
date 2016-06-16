import java.awt.*;
import java.util.*;

public class RelativeLayout implements LayoutManager2, java.io.Serializable {
	//  Used in the constructor
	public final static int X_AXIS = 0;
	public final static int Y_AXIS = 1;

	//  See	setAlignment() method
	public final static float LEADING = 0.0f;
	public final static float CENTER = 0.5f;
	public final static float TRAILING = 1.0f;
	public final static float COMPONENT = -1.0f;

	//  See setRoundingPolicy() method
	public final static int DO_NOTHING = 0;
	public final static int FIRST = 1;
	public final static int LAST = 2;
	public final static int LARGEST = 3;
	public final static int EQUAL = 4;

	private final static int MINIMUM = 0;
	private final static int PREFERRED = 1;

	private HashMap<Component, Float> constraints = new HashMap<Component, Float>();
  
	private int axis;
	private float alignment = CENTER;
	private int gap;
	private int borderGap;
	private boolean fill = false;
	private int fillGap;
	private int roundingPolicy = LARGEST;
  
	public RelativeLayout(){
		this(X_AXIS, 0);
	}
  
	public RelativeLayout(int axis){
		this(axis, 0);
	}
  
	public RelativeLayout(int axis, int gap){
		setAxis( axis );
		setGap( gap );
		setBorderGap( gap );
	}
  
	public int getAxis(){return axis;}
  
	public void setAxis(int axis){
		if (axis != X_AXIS && axis != Y_AXIS)
			throw new IllegalArgumentException("invalid axis specified");
    
		this.axis = axis;
	}
  
	public int getGap(){return gap;}
  
	public void setGap(int n){
		this.gap = n < 0 ? 0 : n;
	}
  
	public int getBorderGap(){return borderGap;}
  
	public void setBorderGap(int n){
		this.borderGap = n < 0 ? 0 : n;
	}
  
	public float getAlignment(){return alignment;}
  
	public void setAlignment(float a){
		this.alignment = a > 1.0f ? 1.0f : a < 0.0f ? -1.0f : a;
	}
  
	public boolean isFill(){return fill;}
  
	public void setFill(boolean fill){
		this.fill = fill;
	}
  
	public int getFillGap(){return fillGap;}
  
	public void setFillGap(int fillGap){
		this.fillGap = fillGap;
	}
  
	public int getRoundingPolicy(){return roundingPolicy;}
  
	public void setRoundingPolicy(int p){
		this.roundingPolicy = p;
	}
  
  public Float getConstraints(Component c){
    return (Float)constraints.get(c);
  }
  
	public void addLayoutComponent(String name, Component c){}
  
	public void addLayoutComponent(Component c, Object fl){
		if (fl != null){
			if (fl instanceof Float){
				constraints.put(c, (Float) fl);
			} else throw new IllegalArgumentException("Constraint parameter must be of type Float");
    }
	}
  
	public void removeLayoutComponent(Component c){}
  
	public Dimension preferredLayoutSize(Container parent){
		synchronized (parent.getTreeLock()){
			return getLayoutSize(parent, PREFERRED);
		}
	}
  
	public Dimension minimumLayoutSize(Container parent){
		synchronized (parent.getTreeLock()){
			return getLayoutSize(parent, MINIMUM);
		}
	}
  
	public void layoutContainer(Container parent){
		synchronized (parent.getTreeLock()){
			if (axis == X_AXIS)
				layoutContainerHorizontally(parent);
			else
				layoutContainerVertically(parent);
		}
	}
  
	private void layoutContainerHorizontally(Container p){
		int cs = p.getComponentCount();
		int vis = getVisibleComponents(p);
		if (cs == 0)return;

		float tot = 0.0f;
		Insets iset = p.getInsets();
		int space = p.getSize().width - iset.left - iset.right
						    - ((vis - 1) * gap) - (2 * borderGap);

		for (int i = 0; i < cs; i++){
			Component c = p.getComponent(i);
			if (!c.isVisible())continue;
			Float fl = constraints.get(c);
      
			if (fl == null){
				Dimension d = c.getPreferredSize();
				space -= d.width;
			} else {
				tot += fl.doubleValue();
			}
		}

		//  Allocate space to each component using relative sizing

		int[] alloc = allocateRelativeSpace(p, space, tot);
    
		int x = iset.left + borderGap;
		int y = iset.top;
		int phei = p.getSize().height - iset.top - iset.bottom;
    
		for (int i = 0; i < cs; i++){
			Component c = p.getComponent(i);
			if (!c.isVisible())continue;
			if (i > 0)x += gap;
			Dimension d = c.getPreferredSize();
			if (fill)d.height = phei - fillGap;
			Float fl = constraints.get(c);

			if (fl == null){
				c.setSize(d);
				int loc = getLocationY(c, phei) + y;
				c.setLocation(x, loc);
				x += d.width;
			} else {
				int width = alloc[i];
				c.setSize(width, d.height);
				int loc = getLocationY(c, phei) + y;
				c.setLocation(x, loc);
				x += width;
			}
		}
	}
  
	private int getLocationY(Component c, int phei){
		float ali = alignment;
		if (ali == COMPONENT)ali = c.getAlignmentY();
		return (int) ((phei - c.getSize().height) * ali);
	}

	/*
	 *  Lay out all the components in the Container along the Y-Axis
	 */
	private void layoutContainerVertically(Container parent){
		int components = parent.getComponentCount();
		int visibleComponents = getVisibleComponents( parent );

		if (components == 0) return;

		//  Determine space available for components using relative sizing

		float relativeTotal = 0.0f;
		Insets insets = parent.getInsets();
		int spaceAvailable = parent.getSize().height
						   - insets.top
						   - insets.bottom
						   - ((visibleComponents - 1) * gap)
						   - (2 * borderGap);

		for (int i = 0 ; i < components ; i++)
		{
			Component component = parent.getComponent(i);

			if (! component.isVisible()) continue;

			Float constraint = constraints.get(component);

			if (constraint == null)
			{
				Dimension d = component.getPreferredSize();
				spaceAvailable -= d.height;
			}
			else
			{
				relativeTotal += constraint.doubleValue();
			}
		}

		//  Allocate space to each component using relative sizing

		int[] relativeSpace = allocateRelativeSpace(parent, spaceAvailable, relativeTotal);

		//  Position each component in the container

		int x = insets.left;
		int y = insets.top + borderGap;
		int insetGap = insets.left + insets.right;
		int parentWidth = parent.getSize().width - insetGap;

		for (int i = 0 ; i < components ; i++)
		{
			Component component = parent.getComponent(i);

			if (! component.isVisible()) continue;

			if (i > 0)
				y += gap;

			Dimension d = component.getPreferredSize();

			if (fill)
				d.width = parentWidth - fillGap;

			Float constraint = constraints.get(component);

			if (constraint == null)
			{
				component.setSize( d );
				int locationX = getLocationX(component, parentWidth) + x;
				component.setLocation(locationX, y);
				y += d.height;
			}
			else
			{
				int height = relativeSpace[i];
				component.setSize(d.width, height);
				int locationX = getLocationX(component, parentWidth) + x;
				component.setLocation(locationX, y);
				y += height;
			}

		}
	}
  
	private int getLocationX(Component component, int width){
		float alignmentX = alignment;
    
		if (alignmentX == COMPONENT)
			alignmentX = component.getAlignmentX();

		float x = (width - component.getSize().width) * alignmentX;
		return (int)x;
	}
  
	private int[] allocateRelativeSpace(Container p, int space, float tot){
		int used = 0;
		int cs = p.getComponentCount();
		int[] alloc = new int[cs];

		for (int i = 0; i < cs; i++){
			alloc[i] = 0;
			if (tot > 0 && space > 0){
				Component c = p.getComponent(i);
				Float fl = constraints.get(c);
        
				if (fl != null){
					int curr = (int)(space * fl.floatValue() / tot);
					alloc[i] = curr;
					used += curr;
				}
			}
		}
		int rem = space - used;
		if (tot > 0 && rem > 0)adjustForRounding(alloc, rem);
    
		return alloc;
	}

	/*
	 *  Because of rounding, all the space has not been allocated
	 *  Override this method to create a custom rounding policy
	 */
	protected void adjustForRounding(int[] relativeSpace, int spaceRemaining){
		switch(roundingPolicy){
			case DO_NOTHING: break;
			case FIRST: adjustFirst(relativeSpace, spaceRemaining); break;
			case LAST: adjustLast(relativeSpace, spaceRemaining); break;
			case LARGEST: adjustLargest(relativeSpace, spaceRemaining); break;
			case EQUAL: adjustEqual(relativeSpace, spaceRemaining); break;
			default: adjustLargest(relativeSpace, spaceRemaining);
		}
	}
  
	private void adjustFirst(int[] relativeSpace, int spaceRemaining){
		for (int i = 0; i < relativeSpace.length; i++){
			if (relativeSpace[i] > 0){
				relativeSpace[i] += spaceRemaining;
				break;
			}
		}
	}
  
	private void adjustLast(int[] relativeSpace, int spaceRemaining){
		for (int i = relativeSpace.length - 1; i > 0; i--){
			if (relativeSpace[i] > 0){
				relativeSpace[i] += spaceRemaining;
				break;
			}
		}
	}
  
	private void adjustLargest(int[] relativeSpace, int spaceRemaining){
		int largest = 0;
		int largestSpace = 0;

		for (int i = 0; i < relativeSpace.length; i++){
			int space = relativeSpace[i];

			if (space > 0){
				if (largestSpace < space){
					largestSpace = space;
					largest = i;
				}
			}
		}

		relativeSpace[largest] += spaceRemaining;
	}

	/*
	 *	Each component using relative sizing gets 1 more pixel
	 *  until all the space is used, starting with the first.
	 */
	private void adjustEqual(int[] relativeSpace, int spaceRemaining){
		for (int i = 0; i < relativeSpace.length; i++){
			if (relativeSpace[i] > 0){
				relativeSpace[i]++;
				spaceRemaining--;

				if (spaceRemaining == 0)break;
			}
		}
	}

	/*
	 *	Determine the Preferred or Minimum layout size
	 */
	private Dimension getLayoutSize(Container parent, int type){
		int width = 0;
		int height = 0;
		int components = parent.getComponentCount();
		int visibleComponents = getVisibleComponents( parent );

		for (int i = 0 ; i < components ; i++){
			Component component = parent.getComponent(i);

			if (!component.isVisible())continue;

			Dimension d = getDimension(component, type);

			if (axis == X_AXIS){
				width += d.width;
				height = Math.max(height, d.height);
			} else {
				width = Math.max(width, d.width);
				height += d.height;
			}
		}

		Insets insets = parent.getInsets();
		int totalGap = ((visibleComponents - 1) * gap) + (2 * borderGap);

		if (axis == X_AXIS){
			width += insets.left + insets.right + totalGap;
			height += insets.top + insets.bottom;
		} else {
			width += insets.left + insets.right;
			height += insets.top + insets.bottom + totalGap;
		}

		Dimension size = new Dimension(width, height);
		return size;
	}

	private int getVisibleComponents(Container t){
		int n = 0;
		for (Component c : t.getComponents()){
      if (c.isVisible())n++;
		}
		return n;
	}

	private Dimension getDimension(Component component, int type){
		switch (type){
			case PREFERRED: return component.getPreferredSize();
			case MINIMUM:   return component.getMinimumSize();
			default: return new Dimension(0, 0);
		}
	}
  
	public Dimension maximumLayoutSize(Container target){
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
  
	public float getLayoutAlignmentX(Container parent){return 0.5f;}
  
	public float getLayoutAlignmentY(Container parent){return 0.5f;}
  
	public void invalidateLayout(Container target){}
  
	public String toString(){
		return getClass().getName() + "[axis=" + axis + ",gap=" + gap + "]";
	}
}
