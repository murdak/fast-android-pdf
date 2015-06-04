package com.vmodev.pdfwriter.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vmodev.pdfwriter.exception.PDFImageIOException;
import com.vmodev.pdfwriter.exception.PDFImageNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Kien on 5/25/2015.
 */

/**
 * A Class that implements a PDF image.
 */
public class ImageElement extends PDFElement {

   private int height;
   private int width;
   private int newHeight;
   private int newWidth;
   private byte[] content;
   private int xObjectID;
   private PredefinedTransform transform = PredefinedTransform.DEGREES_0_ROTATION;

   public byte[] getContent() {
      return content;
   }

   public int getxObjectID() {
      return xObjectID;
   }

   public void setxObjectID(int xObjectID) {
      this.xObjectID = xObjectID;
   }

   /**
    * Class's Constructor
    *
    * @param bitmap    bitmap input
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @throws PDFImageIOException
    */
   public ImageElement(Bitmap bitmap, int newCoordX, int newCoordY) throws PDFImageIOException {
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
      this.content = new byte[outStream.size()];
      this.content = outStream.toByteArray();
      this.height = bitmap.getHeight();
      this.width = bitmap.getWidth();
      this.coordX = newCoordX;
      this.coordY = newCoordY;
      try {
         outStream.close();
      } catch (IOException ex) {
         throw new PDFImageIOException("Get bitmap error ", ex);
      }
      outStream = null;
      bitmap = null;
      System.gc();
   }

   /**
    * Class's Constructor
    *
    * @param bitmap    bitmap input
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @param transform Rotate degree
    * @throws PDFImageIOException
    */
   public ImageElement(Bitmap bitmap, int newCoordX, int newCoordY, PredefinedTransform transform)
      throws PDFImageIOException {
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
      this.content = new byte[outStream.size()];
      this.content = outStream.toByteArray();
      this.height = bitmap.getHeight();
      this.width = bitmap.getWidth();
      this.coordX = newCoordX;
      this.coordY = newCoordY;
      this.transform = transform;
      try {
         outStream.close();
      } catch (IOException ex) {
         throw new PDFImageIOException("Get bitmap error ", ex);
      }
      outStream = null;
      bitmap = null;
      System.gc();
   }

   /**
    * Class's Constructor
    *
    * @param bitmap    bitmap input
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @throws PDFImageIOException
    */
   public ImageElement(Bitmap bitmap, int newCoordX, int newCoordY, int newHeight, int
      newWidth) throws PDFImageIOException {
      int inWidth = bitmap.getWidth();
      int inHeight = bitmap.getHeight();
      int outWidth;
      int outHeight;
      if(newWidth<inWidth||newHeight<inWidth){
         if(inWidth > inHeight){
            outWidth = newWidth;
            outHeight = (inHeight * outWidth) / inWidth;
         } else {
            outHeight = newHeight;
            outWidth = (inWidth * outHeight) / inHeight;
         }
         bitmap = Bitmap.createScaledBitmap(bitmap,outWidth,outHeight,false);
      }
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);

      this.content = new byte[outStream.size()];
      this.content = outStream.toByteArray();
      this.height = bitmap.getHeight();
      this.width = bitmap.getWidth();
      this.coordX = newCoordX;
      this.coordY = newCoordY;
      this.newHeight = newHeight;
      this.newWidth = newWidth;
      try {
         outStream.close();
      } catch (IOException ex) {
         throw new PDFImageIOException("Get bitmap error ", ex);
      }
      outStream = null;
      bitmap = null;
      System.gc();
   }


   /**
    * Class's Constructor
    *
    * @param bitmap    bitmap input
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @param transform Rotate degree
    * @throws PDFImageIOException
    */
   public ImageElement(Bitmap bitmap, int newCoordX, int newCoordY, int newHeight, int
      newWidth, PredefinedTransform transform) throws PDFImageIOException {

      int inWidth = bitmap.getWidth();
      int inHeight = bitmap.getHeight();
      int outWidth;
      int outHeight;
      if(newWidth<inWidth||newHeight<inWidth){
         if(inWidth > inHeight){
            outWidth = newWidth;
            outHeight = (inHeight * outWidth) / inWidth;
         } else {
            outHeight = newHeight;
            outWidth = (inWidth * outHeight) / inHeight;
         }
         bitmap = Bitmap.createScaledBitmap(bitmap,outWidth,outHeight,false);
      }

      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
      this.content = new byte[outStream.size()];
      this.content = outStream.toByteArray();
      this.height = bitmap.getHeight();
      this.width = bitmap.getWidth();
      this.coordX = newCoordX;
      this.coordY = newCoordY;
      this.newHeight = newHeight;
      this.newWidth = newWidth;
      this.transform = transform;
      try {
         outStream.close();
      } catch (IOException ex) {
         throw new PDFImageIOException("Get bitmap error ", ex);
      }
      outStream = null;
      bitmap = null;
      System.gc();
   }


   /**
    * Class's constructor
    *
    * @param imagePath Image's Path + Name
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @throws PDFImageNotFoundException
    * @throws PDFImageIOException
    */
   public ImageElement(String imagePath, int newCoordX, int newCoordY)
      throws PDFImageNotFoundException, PDFImageIOException {
      try {
         Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
         this.content = new byte[outStream.size()];
         this.content = outStream.toByteArray();
         this.height = bitmap.getHeight();
         this.width = bitmap.getWidth();
         this.coordX = newCoordX;
         this.coordY = newCoordY;
         outStream.close();
         outStream = null;
         bitmap = null;
         System.gc();
      } catch (FileNotFoundException ex) {
         throw new PDFImageNotFoundException("Cannot find " + imagePath, ex);
      } catch (IOException ex) {
         throw new PDFImageIOException("Get file error ", ex);
      }
   }

   /**
    * Class's constructor
    *
    * @param imagePath Image's Path + Name
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @param transform Rotate degree
    * @throws PDFImageNotFoundException
    * @throws PDFImageIOException
    */
   public ImageElement(String imagePath, int newCoordX, int newCoordY, PredefinedTransform transform)
      throws PDFImageNotFoundException, PDFImageIOException {
      try {
         Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
         this.content = new byte[outStream.size()];
         this.content = outStream.toByteArray();
         this.height = bitmap.getHeight();
         this.width = bitmap.getWidth();
         this.coordX = newCoordX;
         this.coordY = newCoordY;
         this.transform = transform;
         outStream.close();
         outStream = null;
         bitmap = null;
         System.gc();
      } catch (FileNotFoundException ex) {
         throw new PDFImageNotFoundException("Cannot find " + imagePath, ex);
      } catch (IOException ex) {
         throw new PDFImageIOException("Get file error ", ex);
      }
   }

   /**
    * Class's constructor
    *
    * @param imagePath Image's Path + Name
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @param newHeight New image's height
    * @param newWidth  New image's width
    * @throws PDFImageNotFoundException
    * @throws PDFImageIOException
    */
   public ImageElement(String imagePath, int newCoordX, int newCoordY, int newHeight, int
      newWidth) throws PDFImageNotFoundException, PDFImageIOException {
      try {
         Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
         int inWidth = bitmap.getWidth();
         int inHeight = bitmap.getHeight();
         int outWidth;
         int outHeight;
         if(newWidth<inWidth||newHeight<inWidth){
            if(inWidth > inHeight){
               outWidth = newWidth;
               outHeight = (inHeight * outWidth) / inWidth;
            } else {
               outHeight = newHeight;
               outWidth = (inWidth * outHeight) / inHeight;
            }
            bitmap = Bitmap.createScaledBitmap(bitmap,outWidth,outHeight,false);
         }
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
         this.content = new byte[outStream.size()];
         this.content = outStream.toByteArray();
         this.height = bitmap.getHeight();
         this.width = bitmap.getWidth();
         this.newHeight = newHeight;
         this.newWidth = newWidth;
         this.coordX = newCoordX;
         this.coordY = newCoordY;
         outStream.close();
         outStream = null;
         bitmap = null;
         System.gc();
      } catch (FileNotFoundException ex) {
         throw new PDFImageNotFoundException("Cannot find " + imagePath, ex);
      } catch (IOException ex) {
         throw new PDFImageIOException("Get file error " + imagePath, ex);
      }
   }

   /**
    * Class's constructor
    *
    * @param imagePath Image's Path + Name
    * @param newCoordX X position in the PDF document
    * @param newCoordY Y position in the PDF document
    * @param newHeight New image's height
    * @param newWidth  New image's width
    * @param transform Rotate degree
    * @throws PDFImageNotFoundException
    * @throws PDFImageIOException
    */
   public ImageElement(String imagePath, int newCoordX, int newCoordY, int newHeight,
                       int newWidth, PredefinedTransform transform)
      throws PDFImageNotFoundException, PDFImageIOException {
      try {
         Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
         int inWidth = bitmap.getWidth();
         int inHeight = bitmap.getHeight();
         int outWidth;
         int outHeight;
         if(newWidth<inWidth||newHeight<inWidth){
            if(inWidth > inHeight){
               outWidth = newWidth;
               outHeight = (inHeight * outWidth) / inWidth;
            } else {
               outHeight = newHeight;
               outWidth = (inWidth * outHeight) / inHeight;
            }
            bitmap = Bitmap.createScaledBitmap(bitmap,outWidth,outHeight,false);
         }
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
         this.content = new byte[outStream.size()];
         this.content = outStream.toByteArray();
         this.height = bitmap.getHeight();
         this.width = bitmap.getWidth();
         this.newHeight = newHeight;
         this.newWidth = newWidth;
         this.coordX = newCoordX;
         this.coordY = newCoordY;
         this.transform = transform;
         outStream.close();
         outStream = null;
         bitmap = null;
         System.gc();
      } catch (FileNotFoundException ex) {
         throw new PDFImageNotFoundException("Cannot find " + imagePath, ex);
      } catch (IOException ex) {
         throw new PDFImageIOException("Get file error " + imagePath, ex);
      }
   }

   @Override
   public String getText() {
      StringBuilder resultImage = new StringBuilder();
      StringBuilder imageContent = new StringBuilder();
      imageContent.append("q" + (char) 13 + (char) 10);
      imageContent.append("1 0 0 1 " + String.valueOf(coordX) + " " + String.valueOf(coordY) + " cm" + (char) 13 + (char) 10);
      imageContent.append(transform.getValue() + " 0 0" + " cm" + (char) 13 + (char) 10);
      if (newHeight == 0 || newWidth == 0) {
         imageContent.append(String.valueOf(width) + " 0 0 " + String.valueOf(height) + " cm" + (char) 13 + (char) 10);
      } else {
         imageContent.append(String.valueOf(newWidth) + " 0 0 " + String.valueOf(newHeight) + " cm" + (char) 13 + (char) 10);
      }
      /*if (newHeight == 0 || newWidth == 0) {
         imageContent.append(String.valueOf(width) + " 0 0 " + String.valueOf(height) + " " +
            String.valueOf(coordX) + " " + String.valueOf(coordY) + " cm" + (char) 13 + (char) 10);
      } else {
         imageContent.append(String.valueOf(newWidth) + " 0 0 " + String.valueOf(newHeight) + " " +
            String.valueOf(coordX) + " " + String.valueOf(coordY) + " cm" + (char) 13 + (char) 10);
      }*/
      imageContent.append("/I" + String.valueOf(xObjectID) + " Do" + (char) 13 + (char) 10);
      imageContent.append("Q" + (char) 13 + (char) 10);
      resultImage.append(String.valueOf(objectID) + " 0 obj" + (char) 13 + (char) 10);
      resultImage.append("<<" + (char) 13 + (char) 10);
      resultImage.append("/Length " + String.valueOf(imageContent.length()) + (char) 13 + (char) 10);
      resultImage.append(">>" + (char) 13 + (char) 10);
      resultImage.append("stream" + (char) 13 + (char) 10);
      resultImage.append(imageContent.toString());
      resultImage.append("endstream" + (char) 13 + (char) 10);
      resultImage.append("endobj" + (char) 13 + (char) 10);
      imageContent = null;
      return resultImage.toString();
   }

   public String getXObjectText() {
      StringBuilder resultImage = new StringBuilder();
      //------<XObject Header>------
      resultImage.append(String.valueOf(xObjectID) + " 0 obj" + (char) 13 + (char) 10);
      resultImage.append("<<" + (char) 13 + (char) 10);
      resultImage.append("/Type /XObject" + (char) 13 + (char) 10);
      resultImage.append("/Subtype /Image" + (char) 13 + (char) 10);
      resultImage.append("/Name /I" + String.valueOf(xObjectID) + (char) 13 + (char) 10);
      resultImage.append("/Filter /DCTDecode" + (char) 13 + (char) 10);
      resultImage.append("/Width " + String.valueOf(width) + (char) 13 + (char) 10);
      resultImage.append("/Height " + String.valueOf(height) + (char) 13 + (char) 10);
      resultImage.append("/BitsPerComponent 8" + (char) 13 + (char) 10);
      resultImage.append("/ColorSpace /DeviceRGB" + (char) 13 + (char) 10);
      resultImage.append("/Length " + String.valueOf(content.length) + (char) 13 + (char) 10);
      resultImage.append(">>" + (char) 13 + (char) 10);
      //------</XObject Header>-----
      return resultImage.toString();
   }
}
