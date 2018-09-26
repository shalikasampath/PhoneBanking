package com.project.tts;

//import javax.speech.*;    
//import java.util.*;    
//import javax.speech.synthesis.*;    
//
//public class TexttoSpeech    
//{    
//String speaktext; 
//public void dospeak(String speak)    
//{    
//    speaktext=speak;    
//    String voiceName ="kevin16";    
//    try    
//    {    
//        SynthesizerModeDesc desc = new SynthesizerModeDesc(null,"general",  Locale.US,null,null);    
//        Synthesizer synthesizer =  Central.createSynthesizer(desc);    
//        synthesizer.allocate();    
//        synthesizer.resume();     
//        desc = (SynthesizerModeDesc)  synthesizer.getEngineModeDesc();     
//        Voice[] voices = desc.getVoices();      
//        Voice voice = null;
//        for (int i = 0; i < voices.length; i++)    
//        {    
//            if (voices[i].getName().equals(voiceName))    
//            {    
//                voice = voices[i];    
//                break;     
//            }     
//        }    
//        synthesizer.getSynthesizerProperties().setVoice(voice);    
//        System.out.print("Speaking : "+speaktext);    
//        synthesizer.speakPlainText(speaktext, null);    
//        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);    
//        synthesizer.deallocate();    
//    }    
//    catch (Exception e)   
//    {    
//        String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";    
//        System.out.println(""+e);    
//        System.out.println(message);    
//    }    
//}    
//
//    
//}

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TexttoSpeech{

	// Some available voices are (kevin, kevin16, alan)
	private static final String VOICE_NAME_KEVIN = "kevin";
	private final Voice voice;
	
	public TexttoSpeech() {

		VoiceManager vm = VoiceManager.getInstance();		
		voice = vm.getVoice(VOICE_NAME_KEVIN);
		voice.allocate();
	}

	public void speak(String inputText) {

		if(inputText != null && !inputText.isEmpty()) {
			
			voice.speak(inputText);
		}
	}
	
	public static void main(String[] args) {
		TexttoSpeech obj = new TexttoSpeech();
		obj.speak("20-11-2018");
	}

}