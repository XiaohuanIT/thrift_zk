/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.xiaohuan.rpc.weather_service.entity;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2020-02-18")
public class Weather implements org.apache.thrift.TBase<Weather, Weather._Fields>, java.io.Serializable, Cloneable, Comparable<Weather> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Weather");

  private static final org.apache.thrift.protocol.TField CITY_FIELD_DESC = new org.apache.thrift.protocol.TField("city", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DISTRICT_FIELD_DESC = new org.apache.thrift.protocol.TField("district", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TEMPERATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("temperature", org.apache.thrift.protocol.TType.DOUBLE, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new WeatherStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new WeatherTupleSchemeFactory();

  public java.lang.String city; // required
  public java.lang.String district; // optional
  public double temperature; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CITY((short)1, "city"),
    DISTRICT((short)2, "district"),
    TEMPERATURE((short)3, "temperature");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CITY
          return CITY;
        case 2: // DISTRICT
          return DISTRICT;
        case 3: // TEMPERATURE
          return TEMPERATURE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TEMPERATURE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.DISTRICT};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CITY, new org.apache.thrift.meta_data.FieldMetaData("city", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DISTRICT, new org.apache.thrift.meta_data.FieldMetaData("district", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TEMPERATURE, new org.apache.thrift.meta_data.FieldMetaData("temperature", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Weather.class, metaDataMap);
  }

  public Weather() {
  }

  public Weather(
    java.lang.String city,
    double temperature)
  {
    this();
    this.city = city;
    this.temperature = temperature;
    setTemperatureIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Weather(Weather other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCity()) {
      this.city = other.city;
    }
    if (other.isSetDistrict()) {
      this.district = other.district;
    }
    this.temperature = other.temperature;
  }

  public Weather deepCopy() {
    return new Weather(this);
  }

  @Override
  public void clear() {
    this.city = null;
    this.district = null;
    setTemperatureIsSet(false);
    this.temperature = 0.0;
  }

  public java.lang.String getCity() {
    return this.city;
  }

  public Weather setCity(java.lang.String city) {
    this.city = city;
    return this;
  }

  public void unsetCity() {
    this.city = null;
  }

  /** Returns true if field city is set (has been assigned a value) and false otherwise */
  public boolean isSetCity() {
    return this.city != null;
  }

  public void setCityIsSet(boolean value) {
    if (!value) {
      this.city = null;
    }
  }

  public java.lang.String getDistrict() {
    return this.district;
  }

  public Weather setDistrict(java.lang.String district) {
    this.district = district;
    return this;
  }

  public void unsetDistrict() {
    this.district = null;
  }

  /** Returns true if field district is set (has been assigned a value) and false otherwise */
  public boolean isSetDistrict() {
    return this.district != null;
  }

  public void setDistrictIsSet(boolean value) {
    if (!value) {
      this.district = null;
    }
  }

  public double getTemperature() {
    return this.temperature;
  }

  public Weather setTemperature(double temperature) {
    this.temperature = temperature;
    setTemperatureIsSet(true);
    return this;
  }

  public void unsetTemperature() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TEMPERATURE_ISSET_ID);
  }

  /** Returns true if field temperature is set (has been assigned a value) and false otherwise */
  public boolean isSetTemperature() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TEMPERATURE_ISSET_ID);
  }

  public void setTemperatureIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TEMPERATURE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CITY:
      if (value == null) {
        unsetCity();
      } else {
        setCity((java.lang.String)value);
      }
      break;

    case DISTRICT:
      if (value == null) {
        unsetDistrict();
      } else {
        setDistrict((java.lang.String)value);
      }
      break;

    case TEMPERATURE:
      if (value == null) {
        unsetTemperature();
      } else {
        setTemperature((java.lang.Double)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CITY:
      return getCity();

    case DISTRICT:
      return getDistrict();

    case TEMPERATURE:
      return getTemperature();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CITY:
      return isSetCity();
    case DISTRICT:
      return isSetDistrict();
    case TEMPERATURE:
      return isSetTemperature();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Weather)
      return this.equals((Weather)that);
    return false;
  }

  public boolean equals(Weather that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_city = true && this.isSetCity();
    boolean that_present_city = true && that.isSetCity();
    if (this_present_city || that_present_city) {
      if (!(this_present_city && that_present_city))
        return false;
      if (!this.city.equals(that.city))
        return false;
    }

    boolean this_present_district = true && this.isSetDistrict();
    boolean that_present_district = true && that.isSetDistrict();
    if (this_present_district || that_present_district) {
      if (!(this_present_district && that_present_district))
        return false;
      if (!this.district.equals(that.district))
        return false;
    }

    boolean this_present_temperature = true;
    boolean that_present_temperature = true;
    if (this_present_temperature || that_present_temperature) {
      if (!(this_present_temperature && that_present_temperature))
        return false;
      if (this.temperature != that.temperature)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCity()) ? 131071 : 524287);
    if (isSetCity())
      hashCode = hashCode * 8191 + city.hashCode();

    hashCode = hashCode * 8191 + ((isSetDistrict()) ? 131071 : 524287);
    if (isSetDistrict())
      hashCode = hashCode * 8191 + district.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(temperature);

    return hashCode;
  }

  @Override
  public int compareTo(Weather other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCity()).compareTo(other.isSetCity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.city, other.city);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDistrict()).compareTo(other.isSetDistrict());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDistrict()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.district, other.district);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTemperature()).compareTo(other.isSetTemperature());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTemperature()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.temperature, other.temperature);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Weather(");
    boolean first = true;

    sb.append("city:");
    if (this.city == null) {
      sb.append("null");
    } else {
      sb.append(this.city);
    }
    first = false;
    if (isSetDistrict()) {
      if (!first) sb.append(", ");
      sb.append("district:");
      if (this.district == null) {
        sb.append("null");
      } else {
        sb.append(this.district);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("temperature:");
    sb.append(this.temperature);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (city == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'city' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'temperature' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class WeatherStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public WeatherStandardScheme getScheme() {
      return new WeatherStandardScheme();
    }
  }

  private static class WeatherStandardScheme extends org.apache.thrift.scheme.StandardScheme<Weather> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Weather struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CITY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.city = iprot.readString();
              struct.setCityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DISTRICT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.district = iprot.readString();
              struct.setDistrictIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TEMPERATURE
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.temperature = iprot.readDouble();
              struct.setTemperatureIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetTemperature()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'temperature' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Weather struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.city != null) {
        oprot.writeFieldBegin(CITY_FIELD_DESC);
        oprot.writeString(struct.city);
        oprot.writeFieldEnd();
      }
      if (struct.district != null) {
        if (struct.isSetDistrict()) {
          oprot.writeFieldBegin(DISTRICT_FIELD_DESC);
          oprot.writeString(struct.district);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldBegin(TEMPERATURE_FIELD_DESC);
      oprot.writeDouble(struct.temperature);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WeatherTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public WeatherTupleScheme getScheme() {
      return new WeatherTupleScheme();
    }
  }

  private static class WeatherTupleScheme extends org.apache.thrift.scheme.TupleScheme<Weather> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Weather struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.city);
      oprot.writeDouble(struct.temperature);
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDistrict()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetDistrict()) {
        oprot.writeString(struct.district);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Weather struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.city = iprot.readString();
      struct.setCityIsSet(true);
      struct.temperature = iprot.readDouble();
      struct.setTemperatureIsSet(true);
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.district = iprot.readString();
        struct.setDistrictIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

