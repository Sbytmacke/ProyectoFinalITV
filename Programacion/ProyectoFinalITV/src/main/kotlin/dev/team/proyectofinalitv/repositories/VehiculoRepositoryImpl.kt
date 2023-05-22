package dev.team.proyectofinalitv.repositories

import dev.team.proyectofinalitv.models.Vehiculo
import dev.team.proyectofinalitv.services.database.DataBaseManager
import mu.KotlinLogging

class VehiculoRepositoryImpl(private val databaseManager: DataBaseManager) : VehiculoRepository {

    private val logger = KotlinLogging.logger {}

    override fun findAll(): List<Vehiculo> {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(vehiculo: Vehiculo): Vehiculo {
        TODO("Not yet implemented")
    }

    /**
     * Guarda un vehículo en la base de datos
     * @param el vehículo que guardaremos
     * @return el nuevo vehículo guardado
     */
    override fun save(vehiculo: Vehiculo): Vehiculo {
        logger.debug { "Creando vehículo con matrícula: ${vehiculo.matricula}" }

        databaseManager.openConnection()

        // Seleccionamos la base de datos a la que realizar las consultas
        databaseManager.selectDataBase()

        val statement = databaseManager.connection?.createStatement()

        val sql =
            """
        INSERT INTO Vehiculo (matricula, marca, modelo, fecha_matriculacion, fecha_revision, tipo_motor, tipo_vehiculo, dni_propietario)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """

        val preparedStatement = databaseManager.connection?.prepareStatement(sql)
        preparedStatement?.setString(1, vehiculo.matricula)
        preparedStatement?.setString(2, vehiculo.marca)
        preparedStatement?.setString(3, vehiculo.modelo)
        preparedStatement?.setString(4, vehiculo.fechaMatriculacion.toString())
        preparedStatement?.setString(5, vehiculo.fechaRevision.toString())
        preparedStatement?.setString(6, vehiculo.tipoMotor)
        preparedStatement?.setString(7, vehiculo.tipoVehiculo)
        preparedStatement?.setString(8, vehiculo.dniPropietario)

        preparedStatement?.executeUpdate()

        preparedStatement?.close()
        statement?.close()
        databaseManager.closeConnection()

        return vehiculo
    }

    override fun findById(id: Long): Vehiculo? {
        TODO("Not yet implemented")
    }
}