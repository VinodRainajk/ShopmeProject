package com.shopme.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.admin.Repositories.CategoriesRepositories;
import com.shopme.entities.category;

@Service
public class CategoriesList {
	
	@Autowired
	private CategoriesRepositories categoriesrepo; 
	
	
	 public CategoriesList(CategoriesRepositories categoriesrepo) {
		
			this.categoriesrepo = categoriesrepo;
		}

	
	
    public CategoriesList() {
		super();
	}



	List<category> returnlistvalue =  new ArrayList<>();
    HashMap<category,category> CategoryHashmap = new HashMap<>();
   
	Set<String> elementadded = new HashSet<>();
    HashMap<String,category> AllCategory = new HashMap<>();
    
    public List<category> findRootElement()
    {
      
      //  returnList.add(new category("Electic",null));
        //returnList.add(new category("Chair",null));
        return categoriesrepo.findrootCategory();
    }

    public Optional<category> findChild(category parent)
    {
        for(Entry<category, category> ChildList :  CategoryHashmap.entrySet())
        {
            System.out.println("checkval.getValue() " +ChildList.getValue());
            System.out.println("checkval.getValue() " +ChildList.getKey());
               
            	
            		System.out.println("checkval.getValue() " +ChildList.getValue().getId());
                    System.out.println("parent.getParentName()" +parent.getId());
            		 if(ChildList.getValue().getId().equals(parent.getId()))
                     {
                         System.out.println("return this");
                         return  Optional.of(ChildList.getKey());
                     }
            	
               
            


        }
        
        return Optional.empty();
    }

    public boolean RemoveElement(category category)
    {
        CategoryHashmap.remove(category);
        return true;
    }

    public void childDerviation(category ParentCategory)
    {
        System.out.println("ParentCategory "+ParentCategory.getName());
        Integer depth = 0 ;
        boolean childNotFound = false;
        category currentparent = ParentCategory;

        while (depth!=-1 && !childNotFound)

        {
            System.out.println("depth "+depth);
            System.out.println("childNotFound "+childNotFound);
            System.out.println("CurrentCategory "+currentparent);
            Optional<category> childelement = findChild(currentparent);

           if( childelement.isPresent())
            {
                System.out.println("\"-\".repeat(depth)+currentparent.getName() "+"-".repeat(depth)+currentparent.getName());
             if (!elementadded.contains(currentparent.getName()))
             {
                 returnlistvalue.add(new category(currentparent.getId() ,  "-".repeat(depth)+currentparent.getName()));
                 elementadded.add(currentparent.getName());
             }

                depth++;
                currentparent = childelement.get();
                System.out.println("Hash map is "+ CategoryHashmap);
                childNotFound = false;


            } else
            {

                {
                    System.out.println("Last child ");
                    System.out.println("currentparent.getName() " +currentparent.getName());
                   
                   if(!elementadded.contains(currentparent.getName()))
                   {
                       System.out.println("This is last element ");
                       returnlistvalue.add(new category(currentparent.getId() ,"-".repeat(depth)+currentparent.getName()));
                       elementadded.add(currentparent.getName());
                       System.out.println("\"-\".repeat(depth)+currentparent.getName() "+"-".repeat(depth)+currentparent.getName());
                   }
                    RemoveElement(currentparent);
                    if(currentparent.getParent()!= null )
                    {
                    	 System.out.println("it is not null");
                    	currentparent = AllCategory.get(currentparent.getParent().getName());
                        
                    } 
                  
                    
                    depth--;
                    childNotFound = true;
                    

                }



            }

        }

    }

    public void childDerviationParent()
    {
        System.out.println("Inside childDerviationParent");
        List<category> root = findRootElement();

        for (category category : root)
        {
            System.out.println("category "+category.getName());
            childDerviation(category);
        }
    }

    public List<category> retunrvaluesback()
    {
        System.out.println("Inside retunrvaluesback");
        populatevalue();
        childDerviationParent();

        return returnlistvalue;
    }

	public void populatevalue()
	{
	    System.out.println("Inside populatevalue");
	    //CategoryDummyData categoryDummyData = new CategoryDummyData();
	    List<category> lisofCategories = categoriesrepo.findAll();
	    for(int idx =0; idx< lisofCategories.size() ; idx++)
	    {
	    	 //System.out.println("lisofCategories.get(idx) "+lisofCategories.get(idx));
	        AllCategory.put(lisofCategories.get(idx).getName(), lisofCategories.get(idx));
	        if(lisofCategories.get(idx).getParent()!= null)
	        {
	        	 System.out.println("lisofCategories.get(idx) "+lisofCategories.get(idx));
	        	 System.out.println("lisofCategories.get(idx).getParent() "+lisofCategories.get(idx).getParent());
	            CategoryHashmap.put(lisofCategories.get(idx), lisofCategories.get(idx).getParent());
	        }
	
	    }
	}


}
