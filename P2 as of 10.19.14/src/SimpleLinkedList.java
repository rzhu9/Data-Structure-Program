
public class SimpleLinkedList <E> implements ListADT <E> 
{	
	//constructor
	DblListnode <E> head;
	int numItems;
	
	public SimpleLinkedList(){
		head = null;
		numItems = 0;
	}
	public void add(E item) {	
		DblListnode <E> newnode = new DblListnode <E> (item);
		//Special Case: empty list
		if (head == null) 
		{
			head = newnode;	
		}
		//General Case: non-empty list
		else 
		{
			DblListnode <E> curr = head;
			while (curr.getNext() != null){
				curr = curr.getNext();
			}
			curr.setNext(newnode);
			newnode.setPrev(curr);
		}
		numItems++;
	}

	public void add(int pos, E item){	
		DblListnode <E> curr = head;
		DblListnode <E> newnode = new DblListnode <E> (item);
		if(isValidPos(pos))
		{
			//TODO: Special Case: empty list 	DO WE DO THIS????
			if (head == null) {
				head = newnode;

			}
			//General Case: non-empty list
			else {
				if(pos == numItems)	//checks if right-most node
				{
					this.add(item);
				}
				if(pos == 0)	//checks if left-most node
				{
					head.setPrev(newnode);
					newnode.setNext(head);
				}
				else	//is interior node
				{
					for (int i = 0; i <= pos; i++)
						curr=curr.getNext();

					curr.getPrev().setNext(newnode);
					newnode.setPrev(curr.getPrev());
					newnode.setNext(curr);
					curr.setPrev(newnode);
				}
			}
			numItems++;
		}
		else
		{
			throw new IllegalArgumentException("pos not valid");	//TODO:name me
		}
	}

	public boolean contains(E item){
		DblListnode <E> curr = head;
		for(int i = 0; i < this.size(); i++)
		{				
			if (item == curr.getData())
			{
				return true;
			}
			else
			{
				curr = curr.getNext();
			}
		}
		return false;
	}
	public E get(int pos){
		DblListnode <E> curr = head;
		for(int i = 0; i < pos; i++)
		{				
			curr = curr.getNext();	
		}
	return curr.getData();	
	}
	public boolean isEmpty(){
		if (this.size() == 0)	//if empty lists have head
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public E remove(int pos){	 
		if (isValidPos(pos)){
			DblListnode <E> curr = head;
		E object = get(pos);	//stores data in node returned
		
		if(pos == 0)	//checks if left-most node
		{
			if(numItems != 0)
			{
			
			curr = curr.getNext();
			head = curr;	
			//head.setPrev(null);	//made debugging ez
			numItems --;
			return object;
			
			}
			else	//TODO: handle as an error
			{
				head.setNext(null);
				return object;
			}
		}
		
		if(pos == numItems - 1)	//if right-most node   
		{	
			for (int i = 0; i < pos - 1; i++)	
			{
				curr=curr.getNext();
			}
			curr.setNext(null);
			numItems --;
			return object;
		}
		
		
		else	//is interior node
		{
			for(int i = 0; i < pos; i++)	
			{
				curr= curr.getNext();	
			}
			curr.getPrev().setNext(curr.getNext());
			curr.getNext().setPrev(curr.getPrev());
			numItems--;
		}
		
		return object;
		
		}
		
		else
		throw new IllegalArgumentException();
	}
	public int size(){
		return numItems;
	}

	private boolean isValidPos (int pos)	//finds # of nodes & sees if pos <= to that # aka pos is valid
	{
		if(pos <= size())	
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

