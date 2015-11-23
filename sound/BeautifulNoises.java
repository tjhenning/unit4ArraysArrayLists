public class BeautifulNoises
{
   public static void main(String[] args)
   {
      SoundClip clip = new SoundClip();
      clip.pick();
      clip.show();    

      int[] samples = clip.getSampleValues();
      int[] echo=clip.getSampleValues();
      // In this example, we don't need the sample rate.
      // If you do, call clip.getSampleRate();

      for (int i = 0; i < samples.length; i++)
      {
         samples[i] = 3 * samples[i];
         if (i>=20)
         {
             System.out.println(echo[i-20]);
             samples[i]+=echo[i-20];
             if (samples[i]>=32767)
             {
                 System.out.println(samples[i]+" adfd  "+32767/samples[i]);
                }
         }
      }
      
      clip.show();
   }
}
