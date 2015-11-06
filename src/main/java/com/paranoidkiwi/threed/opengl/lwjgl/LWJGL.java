package com.paranoidkiwi.threed.opengl.lwjgl;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClipPlane;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glFlush;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glIsList;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightf;
import static org.lwjgl.opengl.GL11.glLighti;
import static org.lwjgl.opengl.GL11.glLineStipple;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glLoadMatrix;
import static org.lwjgl.opengl.GL11.glMaterial;
import static org.lwjgl.opengl.GL11.glMaterialf;
import static org.lwjgl.opengl.GL11.glMateriali;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glMultMatrix;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glNormalPointer;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glPolygonStipple;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord1f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexCoord3f;
import static org.lwjgl.opengl.GL11.glTexCoord4f;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameter;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import com.paranoidkiwi.threed.opengl.OpenGL;
import com.paranoidkiwi.threed.opengl.OpenGLUtility;
import com.paranoidkiwi.threed.opengl.constants.Buffer;
import com.paranoidkiwi.threed.opengl.constants.ClientState;
import com.paranoidkiwi.threed.opengl.constants.ClipPlane;
import com.paranoidkiwi.threed.opengl.constants.DisplayListCompilationMode;
import com.paranoidkiwi.threed.opengl.constants.Light;
import com.paranoidkiwi.threed.opengl.constants.LightProperty;
import com.paranoidkiwi.threed.opengl.constants.MaterialProperty;
import com.paranoidkiwi.threed.opengl.constants.MatrixStack;
import com.paranoidkiwi.threed.opengl.constants.PixelFormat;
import com.paranoidkiwi.threed.opengl.constants.PixelType;
import com.paranoidkiwi.threed.opengl.constants.PolygonFace;
import com.paranoidkiwi.threed.opengl.constants.PolygonMode;
import com.paranoidkiwi.threed.opengl.constants.PrimitiveMode;
import com.paranoidkiwi.threed.opengl.constants.State;
import com.paranoidkiwi.threed.opengl.constants.TargetTexture;
import com.paranoidkiwi.threed.opengl.constants.TextureParameter;
import com.paranoidkiwi.threed.opengl.constants.TextureType;

public class LWJGL implements OpenGL, OpenGLUtility {

	private static final LWJGL INSTANCE = new LWJGL();

	// ****************************************************************
	// ** STATICS
	// ****************************************************************

	public static OpenGL getOpenGL() {
		return INSTANCE;
	}

	public static OpenGLUtility getOpenGLUtility() {
		return INSTANCE;
	}

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	protected LWJGL() {
	}

	// ****************************************************************
	// ** ENABLE
	// ****************************************************************

	@Override
	public void enable(State state) {
		glEnable(state.getValue());
	}

	@Override
	public void disable(State state) {
		glDisable(state.getValue());
	}

	@Override
	public void enableClientState(ClientState clienteState) {
		glEnableClientState(clienteState.getValue());
	}

	@Override
	public void disableClientState(ClientState clienteState) {
		glDisableClientState(clienteState.getValue());
	}

	// ****************************************************************
	// ** CLEAR
	// ****************************************************************

	@Override
	public void clearColor(Color color) {
		float[] compArray = color.getRGBComponents(null);

		glClearColor(compArray[0], compArray[1], compArray[2], compArray[3]);
	};

	@Override
	public void clear(Buffer... buffers) {
		int mask = 0;

		for(Buffer buffer : buffers) {
			mask |= buffer.getValue();
		}

		glClear(mask);
	}

	// ****************************************************************
	// ** DRAW
	// ****************************************************************

	@Override
	public void begin(PrimitiveMode mode) {
		glBegin(mode.getValue());
	}

	@Override
	public void end() {
		glEnd();
	}

	@Override
	public void lineStipple(int factor, short pattern) {
		glLineStipple(factor, pattern);
	}

	@Override
	public void polygonStipple(ByteBuffer mask) {
		glPolygonStipple(mask);
	}

	@Override
	public void color(float red, float green, float blue) {
		glColor3f(red, green, blue);
	}

	@Override
	public void color(float red, float green, float blue, float alpha) {
		glColor4f(red, green, blue, alpha);
	}

	@Override
	public void normal(float x, float y, float z) {
		glNormal3f(x, y, z);
	}

	@Override
	public void vertex(float x, float y, float z) {
		glVertex3f(x, y, z);
	}

	@Override
	public void drawArrays(PrimitiveMode mode, int first, int primitiveCount) {
		glDrawArrays(mode.getValue(), first, primitiveCount);
	}

	@Override
	public void polygonMode(PolygonFace face, PolygonMode mode) {
		glPolygonMode(face.getValue(), mode.getValue());
	}

	@Override
	public void cullFace(PolygonFace face) {
		glCullFace(face.getValue());
	}

	@Override
	public void flush() {
		glFlush();
	}

	@Override
	public void viewport(int x, int y, int width, int height) {
		glViewport(x, y, width, height);
	}

	@Override
	public void clipPlane(ClipPlane plane, DoubleBuffer equation) {
		glClipPlane(plane.getValue(), equation);
	}

	// ****************************************************************
	// ** TEXTURES
	// ****************************************************************

	@Override
	public void texImage2D(TargetTexture target, int mipmapReductionLevel, int componentsToUse, int width, int height, int border, PixelFormat format, PixelType type, FloatBuffer image) {
		glTexImage2D(target.getValue(), mipmapReductionLevel, componentsToUse, width, height, border, format.getValue(), type
			.getValue(), image);
	}

	@Override
	public void texParameter(TextureType target, TextureParameter parameter, float value) {
		glTexParameterf(target.getValue(), parameter.getValue(), value);
	}

	@Override
	public void texParameter(TextureType target, TextureParameter parameter, FloatBuffer value) {
		glTexParameter(target.getValue(), parameter.getValue(), value);
	}

	@Override
	public void texCoord(float s) {
		glTexCoord1f(s);
	}

	@Override
	public void texCoord(float s, float t) {
		glTexCoord2f(s, t);
	}

	@Override
	public void texCoord(float s, float t, float r) {
		glTexCoord3f(s, t, r);
	}

	@Override
	public void texCoord(float s, float t, float r, float q) {
		glTexCoord4f(s, t, r, q);
	}

	// ****************************************************************
	// ** LIGHT
	// ****************************************************************

	@Override
	public void light(Light light, LightProperty property, float value) {
		glLightf(light.getValue(), property.getValue(), value);
	}

	@Override
	public void light(Light light, LightProperty property, int value) {
		glLighti(light.getValue(), property.getValue(), value);
	}

	@Override
	public void light(Light light, LightProperty property, FloatBuffer values) {
		glLight(light.getValue(), property.getValue(), values);
	}

	@Override
	public void light(Light light, LightProperty property, IntBuffer values) {
		glLight(light.getValue(), property.getValue(), values);
	}

	@Override
	public void material(PolygonFace face, MaterialProperty property, float value) {
		glMaterialf(face.getValue(), property.getValue(), value);
	};

	@Override
	public void material(PolygonFace face, MaterialProperty property, int value) {
		glMateriali(face.getValue(), property.getValue(), value);
	};

	@Override
	public void material(PolygonFace face, MaterialProperty property, FloatBuffer values) {
		glMaterial(face.getValue(), property.getValue(), values);
	};

	@Override
	public void material(PolygonFace face, MaterialProperty property, IntBuffer values) {
		glMaterial(face.getValue(), property.getValue(), values);
	};

	// ****************************************************************
	// ** MATRIX
	// ****************************************************************

	@Override
	public void matrixMode(MatrixStack stack) {
		glMatrixMode(stack.getValue());
	}

	@Override
	public void loadMatrix(FloatBuffer matrix) {
		glLoadMatrix(matrix);
	}

	@Override
	public void loadIdentity() {
		glLoadIdentity();
	}

	@Override
	public void pushMatrix() {
		glPushMatrix();
	}

	@Override
	public void popMatrix() {
		glPopMatrix();
	}

	@Override
	public void translate(float x, float y, float z) {
		glTranslatef(x, y, z);
	}

	@Override
	public void translate(double x, double y, double z) {
		glTranslated(x, y, z);
	}

	@Override
	public void scale(float x, float y, float z) {
		glScalef(x, y, z);
	}

	@Override
	public void scale(double x, double y, double z) {
		glScaled(x, y, z);
	}

	@Override
	public void rotate(float angle, float x, float y, float z) {
		glRotatef(angle, x, y, z);
	}

	@Override
	public void multMatrix(FloatBuffer matrix) {
		glMultMatrix(matrix);
	}

	// ****************************************************************
	// ** POINTERS
	// ****************************************************************

	@Override
	public void vertexPointer(int groupSize, int stride, FloatBuffer vertexes) {
		glVertexPointer(groupSize, stride, vertexes);
	}

	@Override
	public void normalPointer(int stride, FloatBuffer pointer) {
		glNormalPointer(stride, pointer);
	}

	// ****************************************************************
	// ** DISPLAY LISTS
	// ****************************************************************

	@Override
	public int genLists(int count) {
		return glGenLists(count);
	}

	@Override
	public boolean isList(int index) {
		return glIsList(index);
	}

	@Override
	public void newList(int index, DisplayListCompilationMode mode) {
		glNewList(index, mode.getValue());
	}

	@Override
	public void endList() {
		glEndList();
	}

	@Override
	public void deleteLists(int index, int count) {
		glDeleteLists(index, count);
	}

	@Override
	public void callList(int index) {
		glCallList(index);
	}

	// ****************************************************************
	// ** GLU
	// ****************************************************************

	@Override
	public void perspective(float horizontalFieldOfView, float aspectRatio, float nearZ, float farZ) {
		gluPerspective(horizontalFieldOfView, aspectRatio, nearZ, farZ);
	}

	@Override
	public void lookAt(float cameraX, float cameraY, float cameraZ, float targetX, float targetY, float targetZ, float upX, float upY, float upZ) {
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
}