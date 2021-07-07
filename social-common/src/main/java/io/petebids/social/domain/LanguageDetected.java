/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.petebids.social.domain;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class LanguageDetected extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7627715084386617342L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LanguageDetected\",\"namespace\":\"io.petebids.social.domain\",\"fields\":[{\"name\":\"postId\",\"type\":\"string\"},{\"name\":\"language\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LanguageDetected> ENCODER =
      new BinaryMessageEncoder<LanguageDetected>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LanguageDetected> DECODER =
      new BinaryMessageDecoder<LanguageDetected>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<LanguageDetected> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<LanguageDetected> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<LanguageDetected> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LanguageDetected>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this LanguageDetected to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a LanguageDetected from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a LanguageDetected instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static LanguageDetected fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence postId;
   private java.lang.CharSequence language;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public LanguageDetected() {}

  /**
   * All-args constructor.
   * @param postId The new value for postId
   * @param language The new value for language
   */
  public LanguageDetected(java.lang.CharSequence postId, java.lang.CharSequence language) {
    this.postId = postId;
    this.language = language;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return postId;
    case 1: return language;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: postId = (java.lang.CharSequence)value$; break;
    case 1: language = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'postId' field.
   * @return The value of the 'postId' field.
   */
  public java.lang.CharSequence getPostId() {
    return postId;
  }


  /**
   * Sets the value of the 'postId' field.
   * @param value the value to set.
   */
  public void setPostId(java.lang.CharSequence value) {
    this.postId = value;
  }

  /**
   * Gets the value of the 'language' field.
   * @return The value of the 'language' field.
   */
  public java.lang.CharSequence getLanguage() {
    return language;
  }


  /**
   * Sets the value of the 'language' field.
   * @param value the value to set.
   */
  public void setLanguage(java.lang.CharSequence value) {
    this.language = value;
  }

  /**
   * Creates a new LanguageDetected RecordBuilder.
   * @return A new LanguageDetected RecordBuilder
   */
  public static io.petebids.social.domain.LanguageDetected.Builder newBuilder() {
    return new io.petebids.social.domain.LanguageDetected.Builder();
  }

  /**
   * Creates a new LanguageDetected RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LanguageDetected RecordBuilder
   */
  public static io.petebids.social.domain.LanguageDetected.Builder newBuilder(io.petebids.social.domain.LanguageDetected.Builder other) {
    if (other == null) {
      return new io.petebids.social.domain.LanguageDetected.Builder();
    } else {
      return new io.petebids.social.domain.LanguageDetected.Builder(other);
    }
  }

  /**
   * Creates a new LanguageDetected RecordBuilder by copying an existing LanguageDetected instance.
   * @param other The existing instance to copy.
   * @return A new LanguageDetected RecordBuilder
   */
  public static io.petebids.social.domain.LanguageDetected.Builder newBuilder(io.petebids.social.domain.LanguageDetected other) {
    if (other == null) {
      return new io.petebids.social.domain.LanguageDetected.Builder();
    } else {
      return new io.petebids.social.domain.LanguageDetected.Builder(other);
    }
  }

  /**
   * RecordBuilder for LanguageDetected instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LanguageDetected>
    implements org.apache.avro.data.RecordBuilder<LanguageDetected> {

    private java.lang.CharSequence postId;
    private java.lang.CharSequence language;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.petebids.social.domain.LanguageDetected.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.postId)) {
        this.postId = data().deepCopy(fields()[0].schema(), other.postId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.language)) {
        this.language = data().deepCopy(fields()[1].schema(), other.language);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing LanguageDetected instance
     * @param other The existing instance to copy.
     */
    private Builder(io.petebids.social.domain.LanguageDetected other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.postId)) {
        this.postId = data().deepCopy(fields()[0].schema(), other.postId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.language)) {
        this.language = data().deepCopy(fields()[1].schema(), other.language);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'postId' field.
      * @return The value.
      */
    public java.lang.CharSequence getPostId() {
      return postId;
    }


    /**
      * Sets the value of the 'postId' field.
      * @param value The value of 'postId'.
      * @return This builder.
      */
    public io.petebids.social.domain.LanguageDetected.Builder setPostId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.postId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'postId' field has been set.
      * @return True if the 'postId' field has been set, false otherwise.
      */
    public boolean hasPostId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'postId' field.
      * @return This builder.
      */
    public io.petebids.social.domain.LanguageDetected.Builder clearPostId() {
      postId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'language' field.
      * @return The value.
      */
    public java.lang.CharSequence getLanguage() {
      return language;
    }


    /**
      * Sets the value of the 'language' field.
      * @param value The value of 'language'.
      * @return This builder.
      */
    public io.petebids.social.domain.LanguageDetected.Builder setLanguage(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.language = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'language' field has been set.
      * @return True if the 'language' field has been set, false otherwise.
      */
    public boolean hasLanguage() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'language' field.
      * @return This builder.
      */
    public io.petebids.social.domain.LanguageDetected.Builder clearLanguage() {
      language = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LanguageDetected build() {
      try {
        LanguageDetected record = new LanguageDetected();
        record.postId = fieldSetFlags()[0] ? this.postId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.language = fieldSetFlags()[1] ? this.language : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<LanguageDetected>
    WRITER$ = (org.apache.avro.io.DatumWriter<LanguageDetected>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LanguageDetected>
    READER$ = (org.apache.avro.io.DatumReader<LanguageDetected>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.postId);

    out.writeString(this.language);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.postId = in.readString(this.postId instanceof Utf8 ? (Utf8)this.postId : null);

      this.language = in.readString(this.language instanceof Utf8 ? (Utf8)this.language : null);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.postId = in.readString(this.postId instanceof Utf8 ? (Utf8)this.postId : null);
          break;

        case 1:
          this.language = in.readString(this.language instanceof Utf8 ? (Utf8)this.language : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










