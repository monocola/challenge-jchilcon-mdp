package pe.com.challenge.service.app.util;

/**
 *
 * @author Jorge Homero Chilcón Perez <br/>
 * Enero 16, 2022.
 */
public class Constant {

    public static float FLOAT_CERO = 0;
    public static Integer INTEGER_CERO = 0;
    public static Integer INTEGER_DOS = 2;
    public static Integer INTEGER_ESTADO_PENDIENTE = 1;
    public static Integer INTEGER_ESTADO_COMPLETADO = 2;
    public static Integer INTEGER_ESTADO_RECHAZADO = 3;
    public static Integer INTEGER_ESTADO_ACTIVO = 1;
    public static Integer INTEGER_ESTADO_INACTIVO = 0;


    //percentages:
    public static float FLOAT_0_10 = (float) 0.10;
    public static float FLOAT_0_05 = (float) 0.05;
    public static float FLOAT_0_08 = (float) 0.08;
    public static float FLOAT_0_02 = (float) 0.02;

    public static final String MESSAGE_ERROR = "Ocurrió un error interno.";
    public static final String MESSAGE_NO_CONTENT = "No se han encontrado registros para mostrar.";
    public static final String MESSAGE_NO_FOUND = "El registro no existe.";
    public static final Integer ERROR_404 = 404;
    public static final Integer ERROR_500 = 500;

    public static final String CODE_ERROR_500 = "500";
    public static final String CODE_ERROR_404 = "404";
    public static final String CODE_OK_200 = "200";

    public static final String CODE_INFO_LOG = "INFO";
    public static final String CODE_ERROR_LOG = "ERROR";

    public static final String TIME_LOGGER_FORMAT_HOUR = "HH:mm:ss.SSSSSS";

    public static final String CORRELATION_ID_MDC = "correlationId";
    public static final String TYPE_MDC = "type";
    public static final String ENDPOINT_MDC = "endPoint";
    public static final String START_TIME_MDC = "startTime";
    public static final String END_TIME_MDC = "endTime";
    public static final String STATUS_MDC = "status";
    public static final String TYPE_ERROR_MDC = "typeError";
    public static final String SERVICE_PATH_MDC = "servicePath";
    public static final String MESSAGE_MDC = "message";
    public static final String STACK_TRACE1_MDC = "stackTrace1";
    public static final String STACK_TRACE2_MDC = "stackTrace2";

    public static final Integer TYPE_STOCK = 1;
    public static final Integer TYPE_CONDITION = 2;
    public static final Integer TYPE_DENOMINATION = 3;
    public static final Integer TYPE_RESPONSABLE = 4;
    public static final Integer TYPE_PLATE = 5;
    public static final Integer TYPE_FLOOR = 6;
    public static final Integer TYPE_NO_FILTER = 0;

}
