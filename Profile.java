
import java.util.Arrays;

public class Profile implements ProfileInterface {
    private String name;
    private String about;
    private Set<ProfileInterface> followers = new Set<ProfileInterface>();       

    
    public Profile(String name, String about){
        if (name == null){
            name = "";
        }
        if (about == null){
            about ="";
        }
        this.name= name;
        this.about = about;
    }//end constructor 
    
    public Profile (){
        this.name = "";
        this.about ="";
    }//end default constructor 
    
    @Override
    public void setName(String newName) throws IllegalArgumentException {
        if (newName == null)//test if valid entry
            throw new IllegalArgumentException();
        this.name = newName;//resets name   
    }

    @Override//returns name of profile
    public String getName() {
        return this.name;
    }

    @Override
    public void setAbout(String newAbout) throws IllegalArgumentException {
        if(newAbout == null)//if invalid entry
            throw new IllegalArgumentException();
        else{
            this.about = newAbout;//sets new about me
        }
        
    }

    @Override//return about me String
    public String getAbout() {
        return this.about;
    }

    @Override
    public boolean follow(ProfileInterface other) {
        if(other == null){//wont run if equal null
            return false;
        }
        else{
            try {
                followers.add(other);
            } catch (SetFullException | IllegalArgumentException ex) {
            }
            return true;
        }
    }

    @Override
    public boolean unfollow(ProfileInterface other) {
        if(!followers.contains(other)||other ==null){
            return false;         
        }
        else{
            return followers.remove(other);
        }
    }

    @Override
    public ProfileInterface[] following(int howMany) {
        if(howMany - followers.getCurrentSize() >0){
            howMany= followers.getCurrentSize();
        }
        ProfileInterface[] arr = new ProfileInterface[howMany];
        System.arraycopy(followers.toArray(), 0,arr, 0, howMany);
        return arr; 
    }

    @Override
    public ProfileInterface recommend() {
        ProfileInterface[] arr = following(followers.getCurrentSize());//creates array of this persons followers
        for (int i = 0; i < arr.length; i++){
          ProfileInterface sample = arr[i];//casts sample to type profile
          Profile sample2 = (Profile) sample;
          ProfileInterface[] followersFollowers =sample2.following(100);
          boolean flag = false;
          for(int j=0; j<followersFollowers.length; j++){
              if(!followersFollowers[i].equals(arr[j])&& !followersFollowers[i].equals(this)){//if someone on the following set is identicle 
                  flag =true;
              }     
          }
          if (flag&& !arr[i].equals(this)){//if not the same person and not false
              return arr[i];
          }
        }
        return null;
    }
    
    public boolean equals(Profile sample) {
        if(sample.getAbout().equals(this.about)&& sample.getName().equals(this.name))
            return true;
        else
            return false;
    }
    
}
