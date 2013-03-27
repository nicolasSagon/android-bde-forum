package donnees;

import org.xmlpull.v1.XmlPullParser;

import android.bde_forum.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;

public class AttributGetter {

	public static AttributeSet getAttribute(Context t, int id)
	{
		 XmlPullParser parser = t.getResources().getXml(id);
		 AttributeSet attributes = Xml.asAttributeSet(parser);
		 return attributes;
	}
}
