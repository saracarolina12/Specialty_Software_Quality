import pandas as pd

ruta_excel = './data.xlsx'
df = pd.read_excel(ruta_excel)
pd.set_option('display.float_format', '{:.2f}'.format)
df.rename(columns={'Producto ': 'Producto'}, inplace=True)

''' Suma de unidades vendidas y precio de venta por producto '''
suma_ventas_por_producto = df.groupby('Producto')['Ventas'].sum().reset_index()
suma_unidades_por_producto = df.groupby('Producto')['Unidades vendidas'].sum().reset_index()
suma_costo_almacenamiento_por_producto = df.groupby('Producto')['Costo de almacenamiento'].sum().reset_index()
suma_costo_produccion_por_producto = df.groupby('Producto')['Costo de producción'].sum().reset_index()
suma_costo_distribucion_por_producto = df.groupby('Producto')['Costo de distribución'].sum().reset_index()

merged_df = pd.merge(suma_ventas_por_producto, suma_unidades_por_producto, on='Producto', suffixes=('_ventas', '_unidades'))
merged_df = pd.merge(merged_df, suma_costo_almacenamiento_por_producto, on='Producto')
merged_df = pd.merge(merged_df, suma_costo_produccion_por_producto, on='Producto')
merged_df = pd.merge(merged_df, suma_costo_distribucion_por_producto, on='Producto')

merged_df['Resultado'] = merged_df['Ventas'] - (merged_df['Costo de almacenamiento'] + merged_df['Costo de producción'] + merged_df['Costo de distribución'])
print("* Utilidad Anual por producto:")
merged_df = merged_df.sort_values(by="Resultado", ascending=False)
print(merged_df[['Producto', 'Resultado']])
print(f"----> Total de Utilidad Anual: {merged_df['Resultado'].sum():.2f}")


''' Obtener suma de ventas por producto '''
suma_ventas_por_producto = df.groupby('Producto')['Ventas'].sum().reset_index()
print("\n* Suma de ventas por producto por producto:")
print(suma_ventas_por_producto)

suma_global_ventas = suma_ventas_por_producto['Ventas'].sum()
print("\n* Suma global de ventas:")
print(suma_global_ventas)

