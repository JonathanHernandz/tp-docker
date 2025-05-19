package gd.tp.nivel;

import gd.tp.tarjeta.Tarjeta;
import gd.tp.tarjeta.TarjetaRepository;
import gd.tp.transaccion.Transaccion;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NivelService {
    @Autowired
    private NivelRepository nivelRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public NivelService ( NivelRepository nivelRepository ){
        this.nivelRepository = nivelRepository;
    }

    public boolean nivelesObligatoriosCompletos(){
        List<Nivel> niveles = nivelRepository.findAll();
        return niveles.size() >= 3;
    }


    public List<Nivel> getAllNivel(){
        return nivelRepository.findAll();
    }

    public Nivel saveNivel( Nivel nivel ){
        return nivelRepository.save(nivel);
    }

    public void saveAllNiveles(List<Nivel> niveles){
        List<Nivel> existentes = nivelRepository.findAll(Sort.by("idNivel"));
        int nextId = 1;

        // Si ya existen niveles, encuentra el siguiente ID libre
        if (!existentes.isEmpty()) {
            Set<Long> usados = existentes.stream()
                    .map(Nivel::getIdNivel)
                    .collect(Collectors.toSet());

            for (Nivel nivel : niveles) {
                // Encuentra el próximo ID disponible que no está en "usados"
                while (usados.contains((long) nextId)) {
                    nextId++;
                }
                nivel.setIdNivel((long) nextId);
                usados.add((long) nextId);
            }
        } else {
            for (int i = 0; i < niveles.size(); i++) {
                niveles.get(i).setIdNivel((long) (i + 1));
            }
        }
        nivelRepository.saveAll(niveles);
    }

    public void eliminarNivel(Long idNivel) {
        Nivel nivel = nivelRepository.findById(idNivel)
                .orElseThrow(() -> new RuntimeException("Nivel no encontrado"));

        // Verificar si es un nivel obligatorio (Ejemplo: ID 1, 2 y 3 son obligatorios)
        if (nivel.getIdNivel() <= 3) {
            throw new RuntimeException("No se pueden eliminar niveles obligatorios");
        }

        // Verificar si el nivel está asociado a clientes
        if (tarjetaRepository.existsByNivel(nivel)) {
            throw new RuntimeException("No se puede eliminar un nivel en uso");
        }

        nivelRepository.delete(nivel);
    }

    @Transactional
    public void resetearNiveles(){
        Nivel nivelBasico = nivelRepository.findById(1L).orElseThrow( () -> new RuntimeException("El nivel básico (ID 1) no existe") );

        //Reasignar todas las tarjetas al nivel básico y reiniciar puntos
        List<Tarjeta> tarjetas = tarjetaRepository.findAll();
        for (Tarjeta tarjeta : tarjetas){
            tarjeta.setNivel(nivelBasico);
            tarjeta.setSaldoPuntos(0);
        }
        tarjetaRepository.saveAll(tarjetas);

        // Eliminar todos los niveles excepto el básico


        List<Nivel> niveles = nivelRepository.findAll();
        for(Nivel nivel : niveles){
            if(!nivel.getIdNivel().equals(1L)){
                nivelRepository.delete(nivel);
            }
        }
    }
    public List<Nivel> findAll() {
        return nivelRepository.findAll(Sort.by("idNivel"));
    }

    public Nivel updateNivel(Long idNivel, Nivel nivelDetails){
        Nivel nivel = nivelRepository.findById(idNivel)
                .orElseThrow(()-> new RuntimeException("Nivel no encontrado con ID:" + idNivel));


        nivel.setNombreNivel(nivelDetails.getNombreNivel());
        nivel.setPorcentajeNivel(nivelDetails.getPorcentajeNivel());
        nivel.setPuntosMinimo(nivelDetails.getPuntosMinimo());

        return nivelRepository.save(nivel);
    }

}
