package com.adventnet.nms.example;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

public class AudioNotifier
{
	final int bufSize = 16384;

    Player player = new Player();
	AudioFormat format = null;
	AudioInputStream audioInputStream = null;

	public AudioNotifier()
	{
	}

	public AudioNotifier(URL audioURL)
	{
        createAudioInputStream(audioURL);
	}

	public void createAudioInputStream(URL audioURL) 
	{
		if (audioURL != null )
		{
            try 
			{
                audioInputStream = AudioSystem.getAudioInputStream(audioURL);
				format = audioInputStream.getFormat();
			} catch (Exception ex) 
			{ 
                ex.printStackTrace();
            }
        } 
		else 
		{
            System.err.println("Audio file required.");
        }
    }
	
	public void play()
	{
		player.start();
	}

	public class Player implements Runnable 
	{
        SourceDataLine line;
        Thread thread;
		
        public void start() 
		{
			thread = new Thread(this);
            thread.setName("Player");
			thread.start();
        }

        public void stop() 
		{
            thread = null;
        }
        
        private void showError(String message) 
		{
            if (message != null) 
			{
                System.err.println(message);
			}
            if (thread != null) 
			{
                thread = null;
			} 
        }

        public void run() 
		{
			if (audioInputStream == null) 
			{
                showError("No loaded audio to play back");
                return;
            }
            
			/* Commenting this, as reset is not supported in Jre 1.4.2, in order to fix the issue of unsupported exception thrown for reset/markup at runtime 
            try 
			{
                audioInputStream.reset();
            } 
			catch (Exception e) 
			{
                showError("Unable to reset the stream\n" + e);
                return;
            }
			*/
			
			AudioInputStream playbackInputStream = AudioSystem.getAudioInputStream(format, audioInputStream);
			
            if (playbackInputStream == null) 
			{
                showError("Unable to convert stream of format " + audioInputStream + " to format " + format);
                return;
            }
			
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) 
			{
                showError("Line matching " + info + " not supported.");
                return;
            }
			
			try 
			{
                line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(format, bufSize);
            } 
			catch (LineUnavailableException ex) 
			{ 
                showError("Unable to open the line: " + ex);
                return;
            }

            // play back the captured audio data

            int frameSizeInBytes = format.getFrameSize();
            int bufferLengthInFrames = line.getBufferSize() / 8;
            int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            byte[] data = new byte[bufferLengthInBytes];
            int numBytesRead = 0;

            // start the source data line
            line.start();

            while (thread != null) 
			{
                try 
				{
                    if ((numBytesRead = playbackInputStream.read(data)) == -1) 
					{
                        break;
                    }
                    int numBytesRemaining = numBytesRead;
                    while (numBytesRemaining > 0 ) 
					{
                        numBytesRemaining -= line.write(data, 0, numBytesRemaining);
                    }
                } 
				catch (Exception e) 
				{
                    showError("Error during playback: " + e);
                    break;
                }
            }
            
            if (thread != null) 
			{
                line.drain();
            }
            line.stop();
            line.close();
            line = null;
            showError(null);
        }
    } // End class Player
}


