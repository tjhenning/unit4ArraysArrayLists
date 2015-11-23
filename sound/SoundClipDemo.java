public class SoundClipDemo
{
   public static void main(String[] args)
   {
      SoundClip clip = new SoundClip();
      clip.pick();
      clip.show();    

      int[] samples = clip.getSampleValues();
      int[] echo=new int[samples.length];
      for (int i = 0; i < samples.length; i++)
      {
          echo[i]=samples[i];
      }
      // In this example, we don't need the sample rate.
      // If you do, call clip.getSampleRate();    

      for (int i = 0; i < samples.length-2200; i++)
      {      
             samples[i]=((int)echo[i+2200])+((int)samples[i]);  
             samples[i]=(int)(((double)samples[i])*.546);
        }
      clip.show();
   }
}
