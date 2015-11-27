package com.games.ytokmakov.miu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by YTokmakov on 27.11.2015.
 */
public class GameObjectsLoader {

    final static String LOG_TAG = "XML_PARSING";

    final static String CLASS_MAGE = "mage";

    public static Player getPlayer(Context context, String player_class)
    {
        int playerHeight = 0, playerWidth = 0;
        int states = 0;
        String sprite_name = "";
        GameObjectAnimator animator = null;

        XmlPullParser parser = context.getResources().getXml(R.xml.player_classes);

        try
        {
//            int event = parser.getEventType();

            while ( parser.getEventType() != XmlPullParser.END_DOCUMENT )
            {
                switch(parser.getEventType())
                {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "Doc_start");
                        break;
                    case XmlPullParser.START_TAG:
                        if ( parser.getName().equals(player_class) )
                        {
                            for (int i = 0; i < parser.getAttributeCount(); i++)
                            {
                                switch ( parser.getAttributeName(i) )
                                {
                                    case "height":
                                        playerHeight = Integer.decode(parser.getAttributeValue(i));
                                        break;
                                    case "width":
                                        playerWidth = Integer.decode(parser.getAttributeValue(i));
                                        break;
                                    case "sprite":
                                        sprite_name = parser.getAttributeValue(i);
                                        break;
                                    case "states":
                                        states = Integer.decode(parser.getAttributeValue(i));
                                        break;
                                }
                            }
                        }
                        break;
                }
                parser.next();
//                event = parser.getEventType();
            }
        }
        catch(XmlPullParserException e)
        {

        }
        catch (IOException e)
        {

        }

        int spriteId = context.getResources().getIdentifier(sprite_name, null, context.getPackageName());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap playerSprite = BitmapFactory.decodeResource(context.getResources(), spriteId, options);

        return new Player(playerWidth, playerHeight, playerSprite, states, 90, 90, 30);
    }

}
